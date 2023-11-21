package com.example.TechFellowQueryBuilder.repository;

import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for accessing Comment entities in the database.
 */
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    /**
     * Finds all comments associated with a specific query ID.
     *
     * @param queryId The ID of the query.
     * @return A list of Comment entities associated with the specified query ID.
     */
    List<Comment> findAllByQuery_Id(UUID queryId);
}
