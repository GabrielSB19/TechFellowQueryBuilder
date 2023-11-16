package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents a group of countries entity with code and name.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCountry {

    @Id
    private String groupCountryCode;
    private String groupCountryName;

}