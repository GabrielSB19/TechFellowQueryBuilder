package com.example.TechFellowQueryBuilder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing the request for creating a query data.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetDataRequestDTO {

    @NotNull
    String codeCountry;

    @NotNull
    String codeRegion;

    @NotEmpty
    @NotNull
    @NotBlank
    String gender;

    @NotEmpty
    @NotNull
    @NotBlank
    String ageMin;

    @NotEmpty
    @NotNull
    @NotBlank
    String ageMax;

    @NotEmpty
    @NotNull
    @NotBlank
    String yearMin;

    @NotEmpty
    @NotNull
    @NotBlank
    String yearMax;
}
