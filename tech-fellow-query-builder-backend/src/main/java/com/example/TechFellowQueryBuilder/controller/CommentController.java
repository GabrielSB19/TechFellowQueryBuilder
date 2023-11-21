package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.CommentAPI;
import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import com.example.TechFellowQueryBuilder.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class that handles HTTP requests related to comments.
 */
@RestController
@AllArgsConstructor
public class CommentController implements CommentAPI {

    private final CommentService commentService;

    /**
     * Creates a new comment.
     *
     * @param commentRequestDTO The request DTO containing information for the comment creation.
     * @return The response DTO containing information about the created comment.
     */
    @Override
    public CommentResponseDTO crateComment(CommentRequestDTO commentRequestDTO) {
        return commentService.createComment(commentRequestDTO);
    }

    /**
     * Gets comments by the query ID.
     *
     * @param id The ID of the query for which comments are to be retrieved.
     * @return A list of response DTOs containing information about the comments.
     */
    @Override
    public List<CommentResponseDTO> getCommentByQueryId(String id) {
        return commentService.getAllCommentsByQuery(id);
    }

}
