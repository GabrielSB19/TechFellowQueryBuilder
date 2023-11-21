package com.example.TechFellowQueryBuilder.service.GetDataBigQuery;

import com.example.TechFellowQueryBuilder.dto.request.GetDataRequestDTO;
import com.example.TechFellowQueryBuilder.dto.request.GetDataSelectRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GetDataResponseDTO;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.Country;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class for executing BigQuery queries and processing the results.
 */
@Service
@Slf4j
@AllArgsConstructor
public class GetDataToQueryService {

    GoogleCredentials credentials;

    @Autowired
    public GetDataToQueryService(Environment env, @Value("${spring.credential.google}") String path) {
        try {
            System.out.println(env.getProperty("spring.credential.google"));
            this.credentials = GoogleCredentials.fromStream(new FileInputStream(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Constructs a GetDataToQueryService instance.
     *
     * @throws IOException If an I/O error occurs.
     */
    public GetDataToQueryService() throws IOException {
    }


    /**
     * Groups indicator codes based on age range and gender.
     *
     * @param ageMin The minimum age in the range.
     * @param ageMax The maximum age in the range.
     * @param gender The gender for which indicators are grouped.
     * @return A formatted string of grouped indicator codes.
     */
    private String groupIndicator (String ageMin, String ageMax, String gender){
        List<String> buildIndicatorCode = new ArrayList<>();
        for (int i = Integer.parseInt(ageMin); i <= Integer.parseInt(ageMax); i++) {
            String formattedIndex = (i < 10) ? String.format("%02d", i) : String.valueOf(i);
            String buildIndicatorCodeUnique = "'SP.POP.AG" + formattedIndex + "." + gender + ".UN'";
            buildIndicatorCode.add(buildIndicatorCodeUnique);
        }
        return String.join(", ", buildIndicatorCode);
    }

    /**
     * Builds and returns a SQL query string for retrieving data based on the provided GetDataRequestDTO.
     * The query retrieves data from the 'bigquery-public-data.world_bank_intl_education.international_education' table,
     * filtering by indicator code, country code, and year range.
     *
     * @param getDataRequestDTO The DTO containing parameters for constructing the query.
     * @return A SQL query string for retrieving the specified data.
     */
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

    /**
     * Builds and returns a SQL query string for retrieving region-specific data based on the provided GetDataRequestDTO.
     * The query joins the 'bigquery-public-data.world_bank_intl_education.international_education' and 'bigquery-public-data.world_bank_intl_education.country_summary'
     * tables, filtering by region, indicator code, and year range.
     *
     * @param getDataRequestDTO The DTO containing parameters for constructing the query.
     * @return A SQL query string for retrieving region-specific data.
     */
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

    /**
     * Executes a BigQuery query using the specified configuration.
     *
     * @param queryConfig The configuration for the BigQuery query.
     * @return The result of the BigQuery query job.
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Executes a BigQuery query to retrieve data based on the provided request.
     *
     * @param getDataRequestDTO The request containing parameters for the BigQuery query.
     * @return The response containing the query results.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the query execution is interrupted.
     */
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
        System.out.println(query);
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

        System.out.println(listYears);
        System.out.println(listValues);

        return GetDataResponseDTO.builder()
                .worldType(worldType)
                .years(listYears)
                .values(listValues)
                .query(query)
                .build();
    }

    /**
     * Executes a BigQuery query to retrieve data based on the provided selection request.
     *
     * @param getDataSelectRequestDTO The request containing the SQL query for selection.
     * @return The response containing the selected query results.
     * @throws IOException If an I/O error occurs.
     * @throws InterruptedException If the query execution is interrupted.
     */
    public GetDataResponseDTO doQueryToSelect (GetDataSelectRequestDTO getDataSelectRequestDTO) throws IOException, InterruptedException {
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(getDataSelectRequestDTO.getSqlQuery())
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

        return GetDataResponseDTO.builder().worldType(getDataSelectRequestDTO.getWorldType()).years(listYears).values(listValues).build();
    }
}
