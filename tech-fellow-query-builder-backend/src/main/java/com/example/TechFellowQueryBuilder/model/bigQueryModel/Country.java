package com.example.TechFellowQueryBuilder.model.bigQueryModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a Country entity with country code and name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private String countryCode;
    private String countryName;

}
