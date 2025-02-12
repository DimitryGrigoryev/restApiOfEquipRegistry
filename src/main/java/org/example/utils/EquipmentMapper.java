package org.example.utils;

import org.example.dto.EquipmentDto;
import org.example.dto.EquipmentListDto;
import org.example.repository.entity.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EquipmentMapper {
    Equipment toEntity(EquipmentDto equipmentDto);

//    @Mapping(source = "equipType.id", target = "equipTypeId")
    EquipmentDto toEquipmentDto(Equipment equipment);

//    @Mapping(source = "equipType.id", target = "equipTypeId")
    EquipmentListDto toEquipmentListDto(Equipment equipment);

//    void updateWithNull(EquipmentDto equipmentDto, Equipment equipment);
}