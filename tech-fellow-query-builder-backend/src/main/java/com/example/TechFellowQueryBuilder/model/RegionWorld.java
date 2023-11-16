package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Represents a region world entity with name.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionWorld {
    @Id
    private String regionName;
}