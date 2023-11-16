package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * Represents a Group Age Special entity with code and name.
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupAgeSpecial {

    @Id
    private String groupAgeSpecialCode;
    private String groupAgeSpecialName;
}
