package com.example.TechFellowQueryBuilder.Unit.service.GetDataBigQuery;

import com.example.TechFellowQueryBuilder.dto.request.GetDataRequestDTO;
import com.example.TechFellowQueryBuilder.dto.request.GetDataSelectRequestDTO;
import com.example.TechFellowQueryBuilder.service.BigQuery.BigQueryWorldDataService;
import com.example.TechFellowQueryBuilder.service.GetDataBigQuery.GetDataToQueryService;
import com.google.auth.oauth2.GoogleCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GetDataToQueryServiceTest {

    @Mock
    private GoogleCredentials credentials;

    @Mock
    GetDataToQueryService getDataToQueryService;

    String selectQuery;

    {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        getDataToQueryService = new GetDataToQueryService(credentials);
    }

    @Test
    void testDoQuery() throws IOException, InterruptedException {
        GetDataRequestDTO getDataRequestDTO = new GetDataRequestDTO();
        getDataRequestDTO.setAgeMin("0");
        getDataRequestDTO.setAgeMax("10");
        getDataRequestDTO.setGender("FE");
        getDataRequestDTO.setCodeCountry("COL");
        getDataRequestDTO.setYearMin("2002");
        getDataRequestDTO.setYearMax("2003");

        var doQuery = getDataToQueryService.doQuery(getDataRequestDTO);


        assertNotNull(doQuery);
        assertNotNull(doQuery.getValues());
        assertFalse(doQuery.getValues().isEmpty());
    }

    @Test
    void testDoQueryBadCredentials() throws IOException, InterruptedException {
        GetDataRequestDTO getDataRequestDTO = new GetDataRequestDTO();
        getDataRequestDTO.setAgeMin("0");
        getDataRequestDTO.setAgeMax("10");
        getDataRequestDTO.setGender("FE");
        getDataRequestDTO.setCodeCountry("COL");
        getDataRequestDTO.setYearMin("2002");
        getDataRequestDTO.setYearMax("2003");

        GetDataToQueryService getDataToQueryService1 = new GetDataToQueryService(null);
        try {
            var doQuery = getDataToQueryService1.doQuery(getDataRequestDTO);
            assertNull(doQuery);
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    void testDoQueryToSelect() throws IOException, InterruptedException {
        GetDataSelectRequestDTO getDataSelectRequestDTO = new GetDataSelectRequestDTO();
        getDataSelectRequestDTO.setWorldType("COL");
        getDataSelectRequestDTO.setSqlQuery("SELECT p.year, sum(p.value) as amount_person, FROM `bigquery-public-data.world_bank_intl_education.international_education` as p where p.indicator_code in ('SP.POP.AG00.FE.UN', 'SP.POP.AG01.FE.UN', 'SP.POP.AG02.FE.UN', 'SP.POP.AG03.FE.UN', 'SP.POP.AG04.FE.UN', 'SP.POP.AG05.FE.UN', 'SP.POP.AG06.FE.UN', 'SP.POP.AG07.FE.UN', 'SP.POP.AG08.FE.UN', 'SP.POP.AG09.FE.UN', 'SP.POP.AG10.FE.UN') and country_code = 'COL' and p.year between 2002 and 2003 group by year order by year");

        var doQueryToSelect = getDataToQueryService.doQueryToSelect(getDataSelectRequestDTO);

        assertNotNull(doQueryToSelect);
        assertNotNull(doQueryToSelect.getValues());
        assertFalse(doQueryToSelect.getValues().isEmpty());
    }

    @Test
    void testDoQueryToSelectBadCredentials() throws IOException, InterruptedException {
        GetDataSelectRequestDTO getDataSelectRequestDTO = new GetDataSelectRequestDTO();
        getDataSelectRequestDTO.setWorldType("COL");
        getDataSelectRequestDTO.setSqlQuery("SELECT p.year, sum(p.value) as amount_person, FROM `bigquery-public-data.world_bank_intl_education.international_education` as p where p.indicator_code in ('SP.POP.AG00.FE.UN', 'SP.POP.AG01.FE.UN', 'SP.POP.AG02.FE.UN', 'SP.POP.AG03.FE.UN', 'SP.POP.AG04.FE.UN', 'SP.POP.AG05.FE.UN', 'SP.POP.AG06.FE.UN', 'SP.POP.AG07.FE.UN', 'SP.POP.AG08.FE.UN', 'SP.POP.AG09.FE.UN', 'SP.POP.AG10.FE.UN') and country_code = 'COL' and p.year between 2002 and 2003 group by year order by year");

        GetDataToQueryService getDataToQueryService1 = new GetDataToQueryService(null);
        try {
            var doQueryToSelect = getDataToQueryService1.doQueryToSelect(getDataSelectRequestDTO);
            assertNull(doQueryToSelect);
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }
    }

}
