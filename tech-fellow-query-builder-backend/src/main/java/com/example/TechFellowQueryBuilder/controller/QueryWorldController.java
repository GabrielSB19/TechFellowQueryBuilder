package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.QueryWorldAPI;
import com.example.TechFellowQueryBuilder.dto.response.CountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.GroupCountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.RegionWorldDTO;
import com.example.TechFellowQueryBuilder.service.WorldDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for handling world data queries.
 */
@RestController
@AllArgsConstructor
public class QueryWorldController implements QueryWorldAPI {

    private final WorldDataService worldDataService;

    /**
     * Retrieves a list of countries.
     *
     * @return List of CountryDTO representing countries.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    @Override
    public List<CountryDTO> getCountries() throws InterruptedException, IOException {
        return worldDataService.getCountries();
    }

    /**
     * Retrieves a list of group countries.
     *
     * @return List of GroupCountryDTO representing group countries.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    @Override
    public List<GroupCountryDTO> getGroupCountries() throws InterruptedException, IOException {
        return worldDataService.getGroupCountries();
    }

    /**
     * Retrieves a list of world regions.
     *
     * @return List of RegionWorldDTO representing world regions.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    @Override
    public List<RegionWorldDTO> getRegionsWorld() throws InterruptedException, IOException {
        return worldDataService.getRegionsWorld();
    }
}
