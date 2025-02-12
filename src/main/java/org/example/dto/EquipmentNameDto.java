package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentNameDto {
    @NotEmpty(message = "{validation.registry.equipment.not-empty}")
    @Size(min = 3, max = 255)
    private String name;
}
