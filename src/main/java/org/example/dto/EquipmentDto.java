package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto extends EquipmentListDto{
    @NotEmpty
    private Set<EquipmentIncludeModelDto> includeModels;
}
