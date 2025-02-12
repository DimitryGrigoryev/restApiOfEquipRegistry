package org.example.utils;

import org.example.dto.*;
import org.example.repository.entity.EquipType;
import org.example.repository.entity.Equipment;
import org.example.repository.entity.Model;
import org.example.repository.entity.Options;
import org.springframework.data.util.Pair;

import java.util.function.Function;
import java.util.stream.Collectors;

public class Mappers {
    public static final Function<Pair<OptionsDto, Model>, Options> TO_ENTITY_OPTIONS_FUNCTION = pair ->
            Options.builder()
            .name(pair.getFirst().getName())
            .description(pair.getFirst().getDescription())
                    .modelId(pair.getSecond())
            .build();

    public static final Function<Options, OptionsDto> TO_OPTIONS_DTO_FUNCTION = options ->
            OptionsDto.builder()
            .name(options.getName())
            .description(options.getDescription())
            .build();

    public static final Function<EquipType, EquipTypeDto> TO_EQUIP_TYPE_DTO_FUNCTION = equipType ->
            EquipTypeDto.builder()
                    .name(equipType.getName())
//                    .code(equipType.getCode())
                    .build();

    public static final Function<Equipment, EquipmentListDto> TO_EQUIPMENT_LIST_DTO_FUNCTION = equipment ->
            EquipmentListDto.builder()
                    .name(equipment.getName())
                    .equipTypeId(equipment.getEquipType().getName())
                    .country(equipment.getCountry())
                    .company(equipment.getCompany())
                    .orderOnline(equipment.isOrderOnline())
                    .inCredit(equipment.isInCredit())
                    .build();

    public static final Function<Model, EquipmentIncludeModelDto> TO_AVAIL_MODEL_DTO_FUNCTION =
            model -> EquipmentIncludeModelDto.builder()
                    .name(model.getName())
                    .build();

    public static final Function<Equipment, EquipmentDto> TO_EQUIPMENT_DTO_FUNCTION = equipment ->
            EquipmentDto.builder()
                    .name(equipment.getName())
                    .equipTypeId(equipment.getEquipType().getName())
                    .country(equipment.getCountry())
                    .company(equipment.getCompany())
                    .orderOnline(equipment.isOrderOnline())
                    .inCredit(equipment.isInCredit())
                    .includeModels(
                            equipment.getModel().stream()
                            .map(TO_AVAIL_MODEL_DTO_FUNCTION).collect(Collectors.toSet()))
                    .build();

    public static final Function<Model, ModelDto> TO_MODEL_DTO_FUNCTION = model ->
            ModelDto.builderModelDto()
                    .name(model.getName())
                    .size(model.getSize())
                    .equipmentName(model.getEquipment().getName())
                    .serialNum(model.getSerialNum())
                    .color(model.getColor())
                    .price(model.getPrice())
                    .available(model.isAvailable())
                    .options(model.getOptions()
                            .stream()
                            .map(TO_OPTIONS_DTO_FUNCTION)
                            .collect(Collectors.toSet()))
                    .build();
}
