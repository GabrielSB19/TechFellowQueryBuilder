package com.example.TechFellowQueryBuilder.model.bigQueryModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Represents a region world entity with name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionWorld {
    private String regionName;
}
