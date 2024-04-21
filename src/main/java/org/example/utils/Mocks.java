package org.example.utils;

import org.example.dto.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Mocks {
    public Mocks() {
    }

    public static List<EquipTypeDto> equipTypes() {
        return Arrays.asList(
                new EquipTypeDto("Televisions","Телевизоры"),
                new EquipTypeDto("VacuumCleaners","Пылесосы"),
                new EquipTypeDto("Refrigerators","Холодильники")
        );
    }

    public static ModelNameDto modelName(ModelDto modelDto) {
        return new ModelNameDto(modelDto.getName());
    }

    public static EquipmentNameDto equipmentName(EquipmentListDto equipmentListDto) {
        return new EquipmentNameDto(equipmentListDto.getName());
    }

    public static List<ModelDto> models(){
        return List.of(
                new ModelDto("Televisions", "Televisions", "sl0003333", "grey", 50, 1500.00,
                        Set.of(new OptionsDto("Category", "Маленькие Телевизоры"),
                                new OptionsDto("Technology", "Amoled")
                        ),
                        true)
        );
    }

    public static List<EquipmentDto> equipments() {
        return List.of(
               new EquipmentDto("Телевизор Samsung", "Televisions", "Китай", "Samsung", true, false,
                       Set.of(new AvailModelDto ("SamSL-40210"),
                               new AvailModelDto ("SamSL-40310"),
                               new AvailModelDto ("SamSL-40410")
                       )
               )
        );
    }
}
