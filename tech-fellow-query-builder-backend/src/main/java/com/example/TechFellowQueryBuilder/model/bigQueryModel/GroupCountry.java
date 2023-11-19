package com.example.TechFellowQueryBuilder.model.bigQueryModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a group of countries entity with code and name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCountry {

    private String groupCountryCode;
    private String groupCountryName;

}
