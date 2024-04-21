package org.example.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class ModelNameDto{
    @NotEmpty(message = "{validation.registry.model.not-empty}")
    @Size(min = 3, max = 255)
    private String name;

    public ModelNameDto() {
    }

    public ModelNameDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
