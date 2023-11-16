package com.example.TechFellowQueryBuilder.Unit.service.BigQuery;

import com.example.TechFellowQueryBuilder.service.BigQuery.BigQueryWorldDataService;
import com.google.auth.oauth2.GoogleCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class BigQueryWorldDataServiceTest {

    @Mock
    private GoogleCredentials credentials;

    @Mock
    BigQueryWorldDataService bigQueryWorldDataService;

    {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void setUp() {
        bigQueryWorldDataService = new BigQueryWorldDataService(credentials);
    }

    @Test
    void getCountryTest() throws InterruptedException, IOException {
        var getCountry = bigQueryWorldDataService.getCountries();
        var getOneCountry = getCountry.get(0);

        assertNotNull(getCountry);
        assertNotNull(getOneCountry);

        String toStringRepresentation = getOneCountry.toString();
        assertTrue(toStringRepresentation.contains("countryCode"));
        assertTrue(toStringRepresentation.contains("countryName"));
    }

    @Test
    void badCredentialsCountryTest() throws InterruptedException, IOException {
        BigQueryWorldDataService bigQueryWorldDataService1 = new BigQueryWorldDataService(null);
        try {
            var getCountries = bigQueryWorldDataService1.getCountries();
            assertNull(getCountries);
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    void getRegionWorldTest() throws InterruptedException, IOException {
        var getRegionWorld = bigQueryWorldDataService.getRegionsWorld();
        var getOneRegion = getRegionWorld.get(0);

        assertNotNull(getRegionWorld);
        assertNotNull(getOneRegion);

        String toStringRepresentation = getOneRegion.toString();
        assertTrue(toStringRepresentation.contains("regionName"));
    }

    @Test
    void badCredentialsRegionWorldTest() throws InterruptedException, IOException {
        BigQueryWorldDataService bigQueryWorldDataService1 = new BigQueryWorldDataService(null);
        try {
            var getRegionWorld = bigQueryWorldDataService1.getRegionsWorld();
            assertNull(getRegionWorld);
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    void getGroupCountryTest() throws InterruptedException, IOException {
        var getGroupCountry = bigQueryWorldDataService.getGroupCountries();
        var getOneGroupCountry = getGroupCountry.get(0);

        assertNotNull(getGroupCountry);
        assertNotNull(getOneGroupCountry);

        String toStringRepresentation = getOneGroupCountry.toString();
        assertTrue(toStringRepresentation.contains("groupCountryCode"));
        assertTrue(toStringRepresentation.contains("groupCountryName"));
    }

    @Test
    void badCredentialsGroupCountryTest() throws InterruptedException, IOException {
        BigQueryWorldDataService bigQueryWorldDataService1 = new BigQueryWorldDataService(null);
        try {
            var getGroupCountries = bigQueryWorldDataService1.getGroupCountries();
            assertNull(getGroupCountries);
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }
    }
}
