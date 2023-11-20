package com.example.TechFellowQueryBuilder.repository;

import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findAllByQuery_Id(UUID queryId);
}
