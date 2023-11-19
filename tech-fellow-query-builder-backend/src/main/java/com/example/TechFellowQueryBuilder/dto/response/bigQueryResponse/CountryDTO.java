package com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Country DTO with country code and name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {

    private String countryCode;
    private String countryName;

}
