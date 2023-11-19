package com.example.TechFellowQueryBuilder.service;

import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GroupAgeSpecialDTO;
import com.example.TechFellowQueryBuilder.mapper.GroupAgeSpecialMapper;
import com.example.TechFellowQueryBuilder.service.BigQuery.BigQueryAgeDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for providing age-related data through BigQuery queries.
 */
@Service
@AllArgsConstructor
public class AgeDataService {

    private final BigQueryAgeDataService bigQueryAgeDataService;
    private final GroupAgeSpecialMapper groupAgeSpecialMapper;

    /**
     * Retrieves a list of age-related special groups and maps them to GroupAgeSpecialDTO objects.
     *
     * @return List of GroupAgeSpecialDTO objects representing age-related special groups.
     * @throws InterruptedException If the thread is interrupted while waiting for the BigQuery job to complete.
     * @throws IOException          If an I/O error occurs while interacting with Google BigQuery.
     */
    public List<GroupAgeSpecialDTO> getGroupAgeSpecials() throws InterruptedException, IOException {
        return bigQueryAgeDataService.getGroupAgeSpecial().stream()
                .map(groupAgeSpecialMapper::toDTO)
                .collect(Collectors.toList());
    }
}
