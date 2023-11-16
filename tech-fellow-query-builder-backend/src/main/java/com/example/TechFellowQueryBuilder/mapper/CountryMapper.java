package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.response.CountryDTO;
import com.example.TechFellowQueryBuilder.model.Country;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO toDTO(Country country);
}
