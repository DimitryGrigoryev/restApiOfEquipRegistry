package org.example.utils;

import org.example.dto.ModelDto;
import org.example.repository.entity.Model;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ModelMapper {
//    @Mapping(source = "equipmentName", target = "equipment.name")
    Model toEntity(ModelDto modelDto);

    @AfterMapping
    default void linkOptions(@MappingTarget Model model) {
        model.getOptions().forEach(option -> option.setModelId(model));
    }

//    @Mapping(source = "equipment.name", target = "equipmentName")
    ModelDto toModelDto(Model model);
}