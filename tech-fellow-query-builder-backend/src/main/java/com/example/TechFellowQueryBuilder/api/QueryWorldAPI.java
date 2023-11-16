package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.response.CountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.GroupCountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.RegionWorldDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@RequestMapping(QueryWorldAPI.BASE_URL)
public interface QueryWorldAPI {
    String BASE_URL = "/api/world";

    @GetMapping("/countries")
    List<CountryDTO> getCountries() throws InterruptedException, IOException;

    @GetMapping("/groupCountries")
    List<GroupCountryDTO> getGroupCountries() throws InterruptedException, IOException;

    @GetMapping("/regionsWorld")
    List<RegionWorldDTO> getRegionsWorld() throws InterruptedException, IOException;
}