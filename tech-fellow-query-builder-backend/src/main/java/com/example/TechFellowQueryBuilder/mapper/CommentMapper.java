package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.request.CommentRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.CommentResponseDTO;
import com.example.TechFellowQueryBuilder.model.ownModel.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {


    @Mapping(target = "id", ignore = true)
    Comment toComment(CommentRequestDTO commentRequestDTO);


    @Mapping(target = "userClient", expression = "java(comment.getUserClient().getId())")
    @Mapping(target = "query", expression = "java(comment.getQuery().getId())")
    CommentResponseDTO toCommentResponseDTO(Comment comment);
}
