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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Query query = queryRepository.findById(UUID.fromString(commentRequestDTO.getQueryId())).orElseThrow(() -> new RuntimeException("Query not found"));
        comment.setQuery(query);
        commentRepository.save(comment);
        System.out.println(comment);
        return commentMapper.toCommentResponseDTO(comment);
    }

    public List<CommentResponseDTO> getAllCommentsByQuery(String queryId) {
        List<Comment> commentsByQuery = commentRepository.findAllByQuery_Id(UUID.fromString(queryId));
        System.out.println(commentsByQuery);

        List<CommentResponseDTO> commentResponseDTOS = new ArrayList<>();
        commentsByQuery.stream().map(commentMapper::toCommentResponseDTO).forEach(commentResponseDTOS::add);
        return commentResponseDTOS;
    }
}
