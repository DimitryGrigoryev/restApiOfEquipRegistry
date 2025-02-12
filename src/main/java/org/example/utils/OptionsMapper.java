package org.example.utils;

import org.example.dto.OptionsDto;
import org.example.repository.entity.Options;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OptionsMapper {
    Options toEntity(OptionsDto optionsDto);

    OptionsDto toOptionsDto(Options options);
}