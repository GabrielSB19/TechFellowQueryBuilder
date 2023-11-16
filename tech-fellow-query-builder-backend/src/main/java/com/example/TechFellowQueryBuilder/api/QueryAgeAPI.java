package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.response.GroupAgeSpecialDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * API interface for age-related queries.
 */
@RequestMapping(QueryAgeAPI.BASE_URL)
public interface QueryAgeAPI {

    /**
     * Base URL for age-related queries.
     */
    String BASE_URL = "/api/age";

    /**
     * Retrieves a list of age-related special groups.
     *
     * @return List of GroupAgeSpecialDTO representing age-related special groups.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    @GetMapping("/groupAgeSpecial")
    List<GroupAgeSpecialDTO> getGroupAgeSpecial() throws InterruptedException, IOException;

}
