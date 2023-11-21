package com.example.TechFellowQueryBuilder.dto.response.ownResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object (DTO) representing the response to create a user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private String id;

    private String username;

    private List<QueryResponseDTO> queries;
    private List<CommentResponseDTO> comments;


}
