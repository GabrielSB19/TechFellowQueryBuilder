package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for mapping between Comment entities and corresponding DTOs.
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Maps a CommentRequestDTO to a Comment entity.
     *
     * @param commentRequestDTO The DTO containing information for creating a comment.
     * @return The Comment entity.
     */
    @Mapping(target = "id", ignore = true)
    Comment toComment(CommentRequestDTO commentRequestDTO);

    /**
     * Maps a Comment entity to a CommentResponseDTO.
     *
     * @param comment The Comment entity.
     * @return The CommentResponseDTO containing information about the comment.
     */
    @Mapping(target = "userClient", expression = "java(comment.getUserClient().getId())")
    @Mapping(target = "query", expression = "java(comment.getQuery().getId())")
    @Mapping(target = "username", expression = "java(comment.getUserClient().getUsername())")
    CommentResponseDTO toCommentResponseDTO(Comment comment);
}
