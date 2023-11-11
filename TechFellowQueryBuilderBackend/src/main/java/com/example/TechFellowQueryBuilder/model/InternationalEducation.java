package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternationalEducation {
    private String countryName;
    private String countryCode;
    private String indicatorName;
    private String indicatorCode;
    private Float value;
    private Integer year;
}
