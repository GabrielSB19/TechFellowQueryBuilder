package com.example.TechFellowQueryBuilder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {

    @NotEmpty
    @NotNull
    @NotBlank
    private String comment;

    @NotEmpty
    @NotNull
    @NotBlank
    private String userClientId;

    @NotEmpty
    @NotNull
    @NotBlank
    private String queryId;
}
