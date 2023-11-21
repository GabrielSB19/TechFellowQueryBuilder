package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between UserRequestDTO, UserResponseDTO, and UserClient entities.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    final QueryMapper queryMapper = new QueryMapperImpl();
    final CommentMapper commentMapper = new CommentMapperImpl();

    /**
     * Converts a UserRequestDTO to a UserClient entity.
     *
     * @param userRequestDTO The UserRequestDTO to be converted.
     * @return The corresponding UserClient entity.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "queries", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserClient toUser(UserRequestDTO userRequestDTO);

    /**
     * Converts a UserClient entity to a UserResponseDTO.
     *
     * @param userClient The UserClient entity to be converted.
     * @return The corresponding UserResponseDTO.
     */
    @Mapping(target = "queries", expression = "java(userClient.getQueries().stream().map(queryMapper::toQueryResponseDTO).toList())")
    @Mapping(target = "comments", expression = "java(userClient.getComments().stream().map(commentMapper::toCommentResponseDTO).toList())")
    UserResponseDTO toResponseDTO(UserClient userClient);
}
