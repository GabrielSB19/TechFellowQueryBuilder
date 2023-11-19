package com.example.TechFellowQueryBuilder.Unit.service;

import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.CountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GroupCountryDTO;
import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.RegionWorldDTO;
import com.example.TechFellowQueryBuilder.mapper.CountryMapper;
import com.example.TechFellowQueryBuilder.mapper.GroupCountryMapper;
import com.example.TechFellowQueryBuilder.mapper.RegionWorldMapper;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.Country;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.GroupCountry;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.RegionWorld;
import com.example.TechFellowQueryBuilder.service.BigQuery.BigQueryWorldDataService;
import com.example.TechFellowQueryBuilder.service.WorldDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class WorldDataServiceTest {
    @Mock
    private BigQueryWorldDataService bigQueryWorldDataService;

    @Mock
    private CountryMapper countryMapper;

    @Mock
    private GroupCountryMapper groupCountryMapper;

    @Mock
    private RegionWorldMapper regionWorldMapper;

    @InjectMocks
    private WorldDataService worldDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCountryTest() throws InterruptedException, IOException {

        Country mockCountry = new Country("COL", "Colombia");

        when(bigQueryWorldDataService.getCountries()).thenReturn(List.of(mockCountry));

        when(countryMapper.toDTO(any())).thenAnswer(invocation -> {
            Country source = invocation.getArgument(0);
            assertNotNull(source.getCountryCode());
            assertNotNull(source.getCountryName());

            return new CountryDTO(source.getCountryCode(), source.getCountryName());
        });

        List<CountryDTO> result = worldDataService.getCountries();

        assertEquals(1, result.size());

        verify(bigQueryWorldDataService, times(1)).getCountries();
        verify(countryMapper, times(1)).toDTO(any());
    }

    @Test
    void getCountryErrorTest() throws InterruptedException, IOException {
        when(bigQueryWorldDataService.getCountries()).thenThrow(InterruptedException.class);
        assertThrows(InterruptedException.class, () -> worldDataService.getCountries());
        verify(bigQueryWorldDataService, times(1)).getCountries();
    }

    @Test
    void getGroupCountryTest() throws InterruptedException, IOException {

        GroupCountry mockGroupCountry = new GroupCountry("ARB", "Arabia");

        when(bigQueryWorldDataService.getGroupCountries()).thenReturn(List.of(mockGroupCountry));

        when(groupCountryMapper.toDTO(any())).thenAnswer(invocation -> {
            GroupCountry source = invocation.getArgument(0);
            assertNotNull(source.getGroupCountryCode());
            assertNotNull(source.getGroupCountryName());

            return new GroupCountryDTO(source.getGroupCountryCode(), source.getGroupCountryName());
        });

        List<GroupCountryDTO> result = worldDataService.getGroupCountries();

        assertEquals(1, result.size());

        verify(bigQueryWorldDataService, times(1)).getGroupCountries();
        verify(groupCountryMapper, times(1)).toDTO(any());
    }

    @Test
    void getGroupCountryErrorTest() throws InterruptedException, IOException {
        when(bigQueryWorldDataService.getGroupCountries()).thenThrow(InterruptedException.class);
        assertThrows(InterruptedException.class, () -> worldDataService.getGroupCountries());
        verify(bigQueryWorldDataService, times(1)).getGroupCountries();
    }

    @Test
    void getRegionWorldTest() throws InterruptedException, IOException {

        RegionWorld mockRegion = new RegionWorld("South America");

        when(bigQueryWorldDataService.getRegionsWorld()).thenReturn(List.of(mockRegion));

        when(regionWorldMapper.toDTO(any())).thenAnswer(invocation -> {
            RegionWorld source = invocation.getArgument(0);
            assertNotNull(source.getRegionName());

            return new RegionWorldDTO(source.getRegionName());
        });

        List<RegionWorldDTO> result = worldDataService.getRegionsWorld();

        assertEquals(1, result.size());

        verify(bigQueryWorldDataService, times(1)).getRegionsWorld();
        verify(regionWorldMapper, times(1)).toDTO(any());
    }

    @Test
    void getRegionWorldErrorTest() throws InterruptedException, IOException {
        when(bigQueryWorldDataService.getRegionsWorld()).thenThrow(InterruptedException.class);
        assertThrows(InterruptedException.class, () -> worldDataService.getRegionsWorld());
        verify(bigQueryWorldDataService, times(1)).getRegionsWorld();
    }
}
