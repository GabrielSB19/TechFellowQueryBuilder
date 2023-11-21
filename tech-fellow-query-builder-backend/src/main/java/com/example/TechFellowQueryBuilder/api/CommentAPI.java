package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interface defining operations related to comments in the API.
 */
@RequestMapping(CommentAPI.BASE_URL)
public interface CommentAPI {

    /**
     * Base URL for operations related to comments.
     */
    String BASE_URL = "/api/comment";

    /**
     * Creates a new comment.
     *
     * @param commentRequestDTO The information of the comment to be created.
     * @return The response DTO containing information about the created comment.
     */
    @PostMapping("/create")
    @CrossOrigin("*")
    CommentResponseDTO crateComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO);

    /**
     * Gets comments by the query ID.
     *
     * @param id The ID of the query for which comments are to be retrieved.
     * @return A list of response DTOs containing information about the comments.
     */
    @GetMapping("/get/{id}")
    @CrossOrigin("*")
    List<CommentResponseDTO> getCommentByQueryId(@PathVariable @Valid String id);
}
