package com.example.TechFellowQueryBuilder.controller;

import com.example.TechFellowQueryBuilder.api.CommentAPI;
import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import com.example.TechFellowQueryBuilder.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController implements CommentAPI {

    private final CommentService commentService;
    @Override
    public CommentResponseDTO crateComment(CommentRequestDTO commentRequestDTO) {
        return commentService.createComment(commentRequestDTO);
    }

    @Override
    public List<CommentResponseDTO> getCommentByQueryId(String id) {
        return commentService.getAllCommentsByQuery(id);
    }

}
