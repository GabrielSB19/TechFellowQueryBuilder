package com.example.TechFellowQueryBuilder.mapper;

import com.example.TechFellowQueryBuilder.dto.response.RegionWorldDTO;
import com.example.TechFellowQueryBuilder.model.RegionWorld;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionWorldMapper {
    RegionWorldDTO toDTO(RegionWorld regionWorld);
}
