package com.example.TechFellowQueryBuilder.service;

import com.example.TechFellowQueryBuilder.dto.response.GroupCountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.RegionWorldDTO;
import com.example.TechFellowQueryBuilder.mapper.GroupCountryMapper;
import com.example.TechFellowQueryBuilder.mapper.RegionWorldMapper;
import com.example.TechFellowQueryBuilder.model.GroupCountry;
import com.example.TechFellowQueryBuilder.model.RegionWorld;
import com.example.TechFellowQueryBuilder.service.BigQuery.*;
import com.example.TechFellowQueryBuilder.dto.response.CountryDTO;
import com.example.TechFellowQueryBuilder.mapper.CountryMapper;
import com.example.TechFellowQueryBuilder.model.Country;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WorldDataService {

    private final CountryMapper countryMapper;
    private final GroupCountryMapper groupCountryMapper;
    private final RegionWorldMapper regionWorldMapper;
    private final BigQueryWorldDataService BigQueryWorldDataService;

    public List<CountryDTO> getCountries() throws InterruptedException, IOException {
        List<Country> countries = BigQueryWorldDataService.getCountries();
        return countries.stream()
                .map(countryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<GroupCountryDTO> getGroupCountries() throws InterruptedException, IOException {
        List<GroupCountry> groupCountries = BigQueryWorldDataService.getGroupCountries();
        return groupCountries.stream()
                .map(groupCountryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<RegionWorldDTO> getRegionsWorld() throws InterruptedException, IOException {
        List<RegionWorld> regionsWorld = BigQueryWorldDataService.getRegionsWorld();
        return regionsWorld.stream().map(regionWorldMapper::toDTO).collect(Collectors.toList());
    }
}
