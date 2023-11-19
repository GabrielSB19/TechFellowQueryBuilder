package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.request.QueryRequestDTO;
import com.example.TechFellowQueryBuilder.dto.response.ownResponse.QueryResponseDTO;
import com.example.TechFellowQueryBuilder.model.ownModel.Query;
import com.example.TechFellowQueryBuilder.model.ownModel.UserClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QueryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userClient", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Query toQuery(QueryRequestDTO queryRequestDTO);

    @Mapping(target = "userClient", expression = "java(query.getUserClient().getUsername())")
    @Mapping(target = "comments", ignore = true)
    QueryResponseDTO toQueryResponseDTO(Query query);

}

