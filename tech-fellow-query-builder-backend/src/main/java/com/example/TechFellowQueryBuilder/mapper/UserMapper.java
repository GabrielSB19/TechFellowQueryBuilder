package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.request.UserRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.UserResponseDTO;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "queries", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserClient toUser(UserRequestDTO userRequestDTO);

    @Mapping(target = "queries", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserResponseDTO toResponseDTO(UserClient userClient);
}
