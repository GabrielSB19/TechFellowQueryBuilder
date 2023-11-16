package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.response.GroupAgeSpecialDTO;
import com.example.TechFellowQueryBuilder.model.GroupAgeSpecial;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting GroupAgeSpecial entities to GroupAgeSpecialDTOs.
 */
@Mapper(componentModel = "spring")
public interface GroupAgeSpecialMapper {

    /**
     * Converts a GroupAgeSpecial entity to a GroupAgeSpecialDTO.
     *
     * @param groupAgeSpecial The GroupAgeSpecial entity to be converted.
     * @return The corresponding GroupAgeSpecialDTO.
     */
    GroupAgeSpecialDTO toDTO(GroupAgeSpecial groupAgeSpecial);
}
