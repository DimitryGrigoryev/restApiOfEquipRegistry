package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EquipmentDto extends EquipmentListDto{

    @NotEmpty
    Set<AvailModelDto> availableModels = new HashSet<>();

    public EquipmentDto(String name, String equipTypeCode, String country, String company, boolean orderOnline, boolean inCredit, Set<AvailModelDto> availableModels) {
        super(name, equipTypeCode, country, company, orderOnline, inCredit);
        this.availableModels = availableModels;
    }
}
