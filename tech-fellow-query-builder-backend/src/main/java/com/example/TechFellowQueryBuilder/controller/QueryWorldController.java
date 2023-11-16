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

@RestController
@AllArgsConstructor
public class QueryWorldController implements QueryWorldAPI {

    private final WorldDataService worldDataService;

    @Override
    public List<CountryDTO> getCountries() throws InterruptedException, IOException {
        return worldDataService.getCountries();
    }

    @Override
    public List<GroupCountryDTO> getGroupCountries() throws InterruptedException, IOException {
        return worldDataService.getGroupCountries();
    }

    @Override
    public List<RegionWorldDTO> getRegionsWorld() throws InterruptedException, IOException {
        return worldDataService.getRegionsWorld();
    }
}
