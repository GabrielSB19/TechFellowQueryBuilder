package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.QueryAgeAPI;
import com.example.TechFellowQueryBuilder.dto.response.GroupAgeSpecialDTO;
import com.example.TechFellowQueryBuilder.service.AgeDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for handling age-related queries.
 */
@RestController
@AllArgsConstructor
public class QueryAgeController implements QueryAgeAPI {

    private final AgeDataService ageDataService;

    /**
     * Retrieves a list of age-related special groups.
     *
     * @return List of GroupAgeSpecialDTO representing age-related special groups.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    @Override
    public List<GroupAgeSpecialDTO> getGroupAgeSpecial() throws InterruptedException, IOException {
        return ageDataService.getGroupAgeSpecials();
    }
}
