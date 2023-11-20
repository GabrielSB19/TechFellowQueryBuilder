package com.example.TechFellowQueryBuilder.service;

import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import com.example.TechFellowQueryBuilder.mapper.CommentMapper;
import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import com.example.TechFellowQueryBuilder.repository.CommentRepository;
import com.example.TechFellowQueryBuilder.repository.QueryRepository;
import com.example.TechFellowQueryBuilder.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final QueryRepository queryRepository;

    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO) {
        Comment comment = commentMapper.toComment(commentRequestDTO);

        UserClient userClient = userRepository.findById(UUID.fromString(commentRequestDTO.getUserClientId())).orElseThrow(() -> new RuntimeException("User not found"));
        comment.setUserClient(userClient);
        System.out.println(comment.getUserClient().getUsername());

        Query query = queryRepository.findById(UUID.fromString(commentRequestDTO.getQueryId())).orElseThrow(() -> new RuntimeException("Query not found"));
        comment.setQuery(query);
        System.out.println(comment.getQuery().getId());

        commentRepository.save(comment);

        return commentMapper.toCommentResponseDTO(comment);
    }
}
