package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(CommentAPI.BASE_URL)
public interface CommentAPI {

    String BASE_URL = "/api/comment";

    @PostMapping("/create")
    CommentResponseDTO crateComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO);

    @GetMapping("/get/{id}")
    List<CommentResponseDTO> getCommentByQueryId(@PathVariable @Valid String id);
}
