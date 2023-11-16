package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.response.RegionWorldDTO;
import com.example.TechFellowQueryBuilder.model.RegionWorld;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting RegionWorld entities to RegionWorldDTOs.
 */
@Mapper(componentModel = "spring")
public interface RegionWorldMapper {

    /**
     * Converts a RegionWorld entity to a RegionWorldDTO.
     *
     * @param regionWorld The RegionWorld entity to be converted.
     * @return The corresponding RegionWorldDTO.
     */
    RegionWorldDTO toDTO(RegionWorld regionWorld);
}
