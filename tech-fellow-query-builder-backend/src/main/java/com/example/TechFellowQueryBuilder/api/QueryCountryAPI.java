package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.response.CountryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(QueryCountryAPI.BASE_URL)
public interface QueryCountryAPI {
    String BASE_URL = "/api/countries";

    @GetMapping("/all")
    List<CountryDTO> getCountries() throws InterruptedException;
}
