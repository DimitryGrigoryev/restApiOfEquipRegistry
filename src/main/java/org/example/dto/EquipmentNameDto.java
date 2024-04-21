package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class EquipmentNameDto {
    @NotEmpty(message = "{validation.registry.equipment.not-empty}")
    @Size(min = 3, max = 255)
    private String name;

    public EquipmentNameDto() {
    }

    public EquipmentNameDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
