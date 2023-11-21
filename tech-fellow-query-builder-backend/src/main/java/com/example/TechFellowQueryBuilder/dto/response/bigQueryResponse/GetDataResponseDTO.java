package com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response for get data from a query.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDataResponseDTO {

    private String worldType;
    private List<String> years;
    private List<String> values;
    private String query;

}
