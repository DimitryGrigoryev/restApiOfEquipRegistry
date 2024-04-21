package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

public class ModelDto extends ModelNameDto {
    @NotEmpty
    @JsonProperty("equipment_name")
    private String equipmentName;
    @JsonProperty("serial_num")
    private String serialNum;
    private String color;
    private int size;
    private Double price;
    private boolean available;
    @NotEmpty
    private Set<OptionsDto> options =  new HashSet<>();

    public ModelDto() {
    }


    public ModelDto(String name, String equipmentName, String serialNum, String color, int size, Double price, Set<OptionsDto> options, boolean available) {
        super(name);
        this.equipmentName = equipmentName;
        this.serialNum = serialNum;
        this.color = color;
        this.size = size;
        this.price = price;
        this.options = options;
        this.available = available;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public Set<OptionsDto> getOptions() {
        return options;
    }

    public void setOptions(Set<OptionsDto> options) {
        this.options = options;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
