package com.example.TechFellowQueryBuilder.dto.response.ownResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {

    private String id;

    private String comment;

    private String userClient;

    private String query;
}
