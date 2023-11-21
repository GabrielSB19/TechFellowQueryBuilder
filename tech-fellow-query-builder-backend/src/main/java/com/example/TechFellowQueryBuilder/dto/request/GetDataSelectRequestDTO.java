package com.example.TechFellowQueryBuilder.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the request for a select query.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDataSelectRequestDTO {

    private String sqlQuery;
    private String worldType;

}
