package com.example.TechFellowQueryBuilder.dto.response.ownResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing the response to create a comment.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

    private String id;
    private String comment;
    private UUID userClient;
    private UUID query;
    private String username;
}
