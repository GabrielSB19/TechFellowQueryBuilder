package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.QueryCountryAPI;
import com.example.TechFellowQueryBuilder.dto.response.CountryDTO;
import com.example.TechFellowQueryBuilder.service.WorldDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class QueryCountryController implements QueryCountryAPI {

    private final WorldDataService worldDataService;

    @Override
    public List<CountryDTO> getCountries() throws InterruptedException {
        return worldDataService.getCountries();
    }
}
