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

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequestDTO {

    @NotEmpty
    @NotNull
    @NotBlank
    private String queryName;
    @NotEmpty
    @NotNull
    @NotBlank
    private String description;
    @NotEmpty
    @NotNull
    @NotBlank
    private String userClient;
    @NotEmpty
    @NotNull
    @NotBlank
    private String query;

}
