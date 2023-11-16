package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.response.GroupCountryDTO;
import com.example.TechFellowQueryBuilder.model.GroupCountry;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupCountryMapper {
    GroupCountryDTO toDTO(GroupCountry groupCountry);
}
