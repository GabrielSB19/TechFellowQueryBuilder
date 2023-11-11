package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountrySeriesDefinitions {
    private String countryCode;
    private String seriesCode;
    private String description;
}
