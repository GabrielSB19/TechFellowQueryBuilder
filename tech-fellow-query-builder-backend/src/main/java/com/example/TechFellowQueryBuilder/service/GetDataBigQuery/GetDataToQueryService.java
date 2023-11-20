package com.example.TechFellowQueryBuilder.service.GetDataBigQuery;

import com.example.TechFellowQueryBuilder.dto.request.GetDataRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GetDataResponseDTO;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.Country;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@AllArgsConstructor
public class GetDataToQueryService {

    GoogleCredentials credentials;

    {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public GetDataToQueryService() throws IOException {
    }

    private String groupIndicator (String ageMin, String ageMax, String gender){
        List<String> buildIndicatorCode = new ArrayList<>();
        for (int i = Integer.parseInt(ageMin); i <= Integer.parseInt(ageMax); i++) {
            String formattedIndex = (i < 10) ? String.format("%02d", i) : String.valueOf(i);
            String buildIndicatorCodeUnique = "'SP.POP.AG" + formattedIndex + "." + gender + ".UN'";
            buildIndicatorCode.add(buildIndicatorCodeUnique);
        }
        return String.join(", ", buildIndicatorCode);
    }

    private String buildQuery (GetDataRequestDTO getDataRequestDTO) {
        String setIndicatorCode = groupIndicator(getDataRequestDTO.getAgeMin(), getDataRequestDTO.getAgeMax(), getDataRequestDTO.getGender());
        StringBuilder query = new StringBuilder("SELECT p.year, sum(p.value) as amount_person, FROM `bigquery-public-data.world_bank_intl_education.international_education` as p where p.indicator_code in (")
                .append(setIndicatorCode)
                .append(") and country_code = '")
                .append(getDataRequestDTO.getCodeCountry())
                .append("' and p.year between ")
                .append(getDataRequestDTO.getYearMin()).append(" and ")
                .append(getDataRequestDTO.getYearMax()).append(" group by year order by year");
        return query.toString();
    }

    private String buildQueryRegion(GetDataRequestDTO getDataRequestDTO){
        String setIndicatorCode = groupIndicator(getDataRequestDTO.getAgeMin(), getDataRequestDTO.getAgeMax(), getDataRequestDTO.getGender());
        StringBuilder query = new StringBuilder("SELECT i.year, SUM(i.value) as amount_person FROM `bigquery-public-data.world_bank_intl_education.international_education` as i JOIN `bigquery-public-data.world_bank_intl_education.country_summary` as s ON i.country_code = s.country_code WHERE s.region = '")
                .append(getDataRequestDTO.getCodeRegion())
                .append("' AND i.indicator_code IN (")
                .append(setIndicatorCode).append(") AND i.year BETWEEN ")
                .append(getDataRequestDTO.getYearMin()).append(" AND ")
                .append(getDataRequestDTO.getYearMax())
                .append(" GROUP BY i.year ORDER BY i.year");
        return query.toString();
    }

    private Job config(QueryJobConfiguration queryConfig) throws IOException {
        BigQuery bigquery = BigQueryOptions.newBuilder()
                .setCredentials(credentials)
                .build().getService();
        String jobIdStr = UUID.randomUUID().toString();
        JobId jobId = JobId.of(jobIdStr);
        Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());
        try {
            queryJob = queryJob.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (queryJob == null) {
            throw new RuntimeException("Connect Failed");
        } else if (queryJob.getStatus().getError() != null) {
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        return queryJob;
    }

    public GetDataResponseDTO doQuery(GetDataRequestDTO getDataRequestDTO) throws IOException, InterruptedException {
        String query;
        String worldType;
        if(!getDataRequestDTO.getCodeCountry().isEmpty()){
            worldType = getDataRequestDTO.getCodeCountry();
            query = buildQuery(getDataRequestDTO);
        } else {
            worldType = getDataRequestDTO.getCodeRegion();
            query = buildQueryRegion(getDataRequestDTO);
        }
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(query)
                        .setUseLegacySql(false)
                        .build();

        Job queryJob = config(queryConfig);

        TableResult result = queryJob.getQueryResults();
        List<String> listYears = StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> row.get("year").getStringValue())
                .toList();

        List<String> listValues = StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> row.get("amount_person").getStringValue())
                .toList();

        return GetDataResponseDTO.builder()
                .worldType(worldType)
                .years(listYears)
                .values(listValues)
                .build();
    }
}
