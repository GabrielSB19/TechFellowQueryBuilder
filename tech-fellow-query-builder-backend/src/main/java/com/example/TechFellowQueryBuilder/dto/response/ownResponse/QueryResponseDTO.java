package com.example.TechFellowQueryBuilder.dto.response.ownResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response to create a query.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponseDTO {

    private String id;
    private String queryName;
    private String description;
    private String userClient;
    private String query;
    private List<CommentResponseDTO> comments;
    private String worldType;
}
