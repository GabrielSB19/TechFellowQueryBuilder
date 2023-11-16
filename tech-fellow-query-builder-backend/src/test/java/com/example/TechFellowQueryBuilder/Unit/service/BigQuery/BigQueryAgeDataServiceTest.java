package com.example.TechFellowQueryBuilder.Unit.service.BigQuery;

import com.example.TechFellowQueryBuilder.service.BigQuery.BigQueryAgeDataService;
import com.google.auth.oauth2.GoogleCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class BigQueryAgeDataServiceTest {

    @Mock
    private GoogleCredentials credentials;
    @Mock
    BigQueryAgeDataService bigQueryAgeDataService;

    {
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/credentials.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @BeforeEach
    void setUp() {
        bigQueryAgeDataService = new BigQueryAgeDataService(credentials);
    }

    @Test
    void getGroupAgeSpecialTest() throws InterruptedException, IOException {
        var getGroupAgeSpecial = bigQueryAgeDataService.getGroupAgeSpecial();
        var getOneGroupAgeSpecial = getGroupAgeSpecial.get(0);

        assertNotNull(getGroupAgeSpecial);
        assertNotNull(getOneGroupAgeSpecial);

        String toStringRepresentation = getOneGroupAgeSpecial.toString();
        assertTrue(toStringRepresentation.contains("groupAgeSpecialCode"));
        assertTrue(toStringRepresentation.contains("groupAgeSpecialName"));
    }

    @Test
    void noCredentialGroupAgeSpecialTest() throws InterruptedException, IOException, NullPointerException{
        BigQueryAgeDataService bigQueryAgeDataService1 = new BigQueryAgeDataService(null);
        try {
            var getGroupAgeSpecial = bigQueryAgeDataService1.getGroupAgeSpecial();
            assertNull(getGroupAgeSpecial);
        } catch (NullPointerException e) {
            assertNull(e.getMessage());
        }
    }
}
