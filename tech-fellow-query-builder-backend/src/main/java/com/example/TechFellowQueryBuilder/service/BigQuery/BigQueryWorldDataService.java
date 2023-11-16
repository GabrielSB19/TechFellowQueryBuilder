package com.example.TechFellowQueryBuilder.service.BigQuery;

import com.example.TechFellowQueryBuilder.model.Country;
import com.example.TechFellowQueryBuilder.model.GroupCountry;
import com.example.TechFellowQueryBuilder.model.RegionWorld;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@AllArgsConstructor
public class BigQueryWorldDataService {

    GoogleCredentials credentials;

    {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BigQueryWorldDataService() throws IOException {
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

    public List<Country> getCountries() throws InterruptedException, IOException {

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT country_code, table_name  FROM `bigquery-public-data.world_bank_intl_education.country_summary` where region is not null")
                        .setUseLegacySql(false)
                        .build();

        Job queryJob = config(queryConfig);

        TableResult result = queryJob.getQueryResults();

        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> Country.builder()
                        .countryCode(row.get("country_code").getStringValue())
                        .countryName(row.get("table_name").getStringValue())
                        .build())
                .collect(Collectors.toList());
    }

    public List<GroupCountry> getGroupCountries() throws InterruptedException, IOException {

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT country_code, table_name  FROM `bigquery-public-data.world_bank_intl_education.country_summary` where region is null")
                        .setUseLegacySql(false)
                        .build();

        Job queryJob = config(queryConfig);
        TableResult result = queryJob.getQueryResults();

        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> GroupCountry.builder()
                        .groupCountryCode(row.get("country_code").getStringValue())
                        .groupCountryName(row.get("table_name").getStringValue())
                        .build())
                .collect(Collectors.toList());
    }

    public List<RegionWorld> getRegionsWorld() throws InterruptedException, IOException{
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT region FROM `bigquery-public-data.world_bank_intl_education.country_summary` where region is not null GROUP BY region")
                        .setUseLegacySql(false)
                        .build();

        Job queryJob = config(queryConfig);
        TableResult result = queryJob.getQueryResults();

        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> RegionWorld.builder()
                        .regionName(row.get("region").getStringValue())
                        .build())
                .collect(Collectors.toList());
    }
}
