package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QueryMapper {

    final CommentMapper commentMapper = new CommentMapperImpl();

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userClient", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Query toQuery(QueryRequestDTO queryRequestDTO);

    @Mapping(target = "userClient", expression = "java(query.getUserClient().getUsername())")
    @Mapping(target = "comments", expression = "java(query.getComments().stream().map(commentMapper::toCommentResponseDTO).toList())")
    QueryResponseDTO toQueryResponseDTO(Query query);

}

