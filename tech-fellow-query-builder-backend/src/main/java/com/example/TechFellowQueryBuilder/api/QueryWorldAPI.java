package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.CountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GroupCountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.RegionWorldDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

/**
 * API interface for world data queries.
 */
@RequestMapping(QueryWorldAPI.BASE_URL)
public interface QueryWorldAPI {

    /**
     * Base URL for world data queries.
     */
    String BASE_URL = "/api/world";

    /**
     * Retrieves a list of countries.
     *
     * @return List of CountryDTO representing countries.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */

    @GetMapping("/countries")
    @CrossOrigin("*")
    List<CountryDTO> getCountries() throws InterruptedException, IOException;

    /**
     * Retrieves a list of group countries.
     *
     * @return List of GroupCountryDTO representing group countries.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    @GetMapping("/groupCountries")
    @CrossOrigin("*")
    List<GroupCountryDTO> getGroupCountries() throws InterruptedException, IOException;

    /**
     * Retrieves a list of world regions.
     *
     * @return List of RegionWorldDTO representing world regions.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    @GetMapping("/regionsWorld")
    @CrossOrigin("*")
    List<RegionWorldDTO> getRegionsWorld() throws InterruptedException, IOException;
}