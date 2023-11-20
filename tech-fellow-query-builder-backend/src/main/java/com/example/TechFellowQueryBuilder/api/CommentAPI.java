package com.example.TechFellowQueryBuilder.api;

import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(CommentAPI.BASE_URL)
public interface CommentAPI {

    String BASE_URL = "/api/comment";

    @PostMapping("/create")
    CommentResponseDTO crateComment(@RequestBody @Valid CommentRequestDTO commentRequestDTO);

}
