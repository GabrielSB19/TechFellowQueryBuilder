package com.example.TechFellowQueryBuilder.dto.request;

import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data Transfer Object (DTO) representing the request for creating a query.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequestDTO {

    @NotEmpty
    @NotNull
    @NotBlank
    private String description;
    @NotEmpty
    @NotNull
    @NotBlank
    private String queryName;
    @NotEmpty
    @NotNull
    @NotBlank
    private String userClient;
    @NotEmpty
    @NotNull
    @NotBlank
    private String query;
    @NotEmpty
    @NotNull
    @NotBlank
    private String worldType;

    @NotNull
    private String codeCountry;

    @NotNull
    private String codeRegion;

    @NotEmpty
    @NotNull
    @NotBlank
    private String gender;

    @NotEmpty
    @NotNull
    @NotBlank
    private String ageMin;

    @NotEmpty
    @NotNull
    @NotBlank
    private String ageMax;

    @NotEmpty
    @NotNull
    @NotBlank
    private String yearMin;

    @NotEmpty
    @NotNull
    @NotBlank
    private String yearMax;

}
