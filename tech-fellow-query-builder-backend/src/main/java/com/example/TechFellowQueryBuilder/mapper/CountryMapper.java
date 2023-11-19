package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.CountryDTO;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.Country;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting Country entities to CountryDTOs.
 */
@Mapper(componentModel = "spring")
public interface CountryMapper {
    /**
     * Converts a Country entity to a CountryDTO.
     *
     * @param country The Country entity to be converted.
     * @return The corresponding CountryDTO.
     */
    CountryDTO toDTO(Country country);
}
