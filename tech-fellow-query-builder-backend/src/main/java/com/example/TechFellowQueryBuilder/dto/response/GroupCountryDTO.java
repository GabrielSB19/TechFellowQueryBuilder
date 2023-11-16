package com.example.TechFellowQueryBuilder.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupCountryDTO {

    private String groupCountryCode;
    private String groupCountryName;
}
