package com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a region world DTO with name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionWorldDTO {

    private String regionName;

}
