package com.example.TechFellowQueryBuilder.service.BigQuery;

import com.example.TechFellowQueryBuilder.model.bigQueryModel.GroupAgeSpecial;
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
 * Service class for interacting with Google BigQuery to retrieve age-related data.
 */
@Service
@Slf4j
@AllArgsConstructor
public class BigQueryAgeDataService {

    GoogleCredentials credentials;

    {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the BigQueryAgeDataService with the provided GoogleCredentials.
     */
    public BigQueryAgeDataService() throws IOException {
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
     * Retrieves a list of age-related special groups with their series codes and indicator names from BigQuery.
     *
     * @return List of GroupAgeSpecial objects representing age-related special groups.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    public List<GroupAgeSpecial> getGroupAgeSpecial() throws InterruptedException, IOException {

        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(
                                "SELECT series_code, indicator_name FROM `bigquery-public-data.world_bank_intl_education.series_summary` where topic = \"Population\" and short_definition is null AND NOT (indicator_name LIKE \"Age%\")")
                        .setUseLegacySql(false)
                        .build();

        Job queryJob = config(queryConfig);

        TableResult result = queryJob.getQueryResults();

        return StreamSupport.stream(result.iterateAll().spliterator(), false)
                .map(row -> GroupAgeSpecial.builder()
                        .groupAgeSpecialCode(row.get("series_code").getStringValue())
                        .groupAgeSpecialName(row.get("indicator_name").getStringValue())
                        .build())
                .collect(Collectors.toList());
    }
}
