package org.example.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class EquipmentDto extends EquipmentListDto{

    @NotEmpty
    private Set<AvailModelDto> availableModels = new HashSet<>();

    public EquipmentDto() {
    }

    public EquipmentDto(String name, String equipTypeCode, String country, String company, boolean orderOnline, boolean inCredit, Set<AvailModelDto> availableModels) {
        super(name, equipTypeCode, country, company, orderOnline, inCredit);
        this.availableModels = availableModels;
    }


    public Set<AvailModelDto> getAvailableModels() {
        return availableModels;
    }

    public void setAvailableModels(Set<AvailModelDto> availableModels) {
        this.availableModels = availableModels;
    }
}
