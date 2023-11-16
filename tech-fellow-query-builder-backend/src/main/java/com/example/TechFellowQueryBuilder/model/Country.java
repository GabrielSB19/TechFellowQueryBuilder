package com.example.TechFellowQueryBuilder.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    private String countryCode;
    private String countryName;

}
