package com.example.TechFellowQueryBuilder.model.bigQueryModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
/**
 * Represents a Group Age Special entity with code and name.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupAgeSpecial {

    private String groupAgeSpecialCode;
    private String groupAgeSpecialName;
}
