package org.example.utils;

import org.example.dto.EquipTypeDto;
import org.example.repository.entity.EquipType;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipTypeMapper {
    EquipType toEntity(EquipTypeDto equipTypeDto);

    EquipTypeDto toEquipTypeDto(EquipType equipType);
}