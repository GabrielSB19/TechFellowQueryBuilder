package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.response.bigQueryResponse.GroupCountryDTO;
import com.example.TechFellowQueryBuilder.model.bigQueryModel.GroupCountry;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting GroupCountry entities to GroupCountryDTOs.
 */
@Mapper(componentModel = "spring")
public interface GroupCountryMapper {

    /**
     * Converts a GroupCountry entity to a GroupCountryDTO.
     *
     * @param groupCountry The GroupCountry entity to be converted.
     * @return The corresponding GroupCountryDTO.
     */
    GroupCountryDTO toDTO(GroupCountry groupCountry);
}
