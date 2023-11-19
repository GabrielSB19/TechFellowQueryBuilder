package com.example.TechFellowQueryBuilder.service.BigQuery;

import com.example.TechFellowQueryBuilder.model.bigQueryModel.Country;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.GroupCountry;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.RegionWorld;
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


/**
 * Service class for interacting with Google BigQuery to retrieve world data.
 */
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

    /**
     * Initializes the BigQueryWorldDataService with the provided GoogleCredentials.
     */
    public BigQueryWorldDataService() throws IOException {
    }

    /**
     * Configures and executes a BigQuery job based on the provided QueryJobConfiguration.
     *
     * @param queryConfig QueryJobConfiguration to configure the BigQuery job.
     * @return The executed BigQuery job.
     * @throws IOException If an I/O error occurs while interacting with Google BigQuery.
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
     * Retrieves a list of countries with their country codes and names from BigQuery.
     *
     * @return List of Country objects representing countries.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
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

    /**
     * Retrieves a list of group countries with their country codes and names from BigQuery.
     *
     * @return List of GroupCountry objects representing group countries.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
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

    /**
     * Retrieves a list of world regions from BigQuery.
     *
     * @return List of RegionWorld objects representing world regions.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
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
