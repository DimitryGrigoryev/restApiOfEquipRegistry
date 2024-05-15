package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelDto extends ModelNameDto {
    @NotEmpty
    @JsonProperty("equipment_name")
    String equipmentName;
    @JsonProperty("serial_num")
    String serialNum;
    String color;
    int size;
    Double price;
    boolean available;
    @NotEmpty
    Set<OptionsDto> options =  new HashSet<>();

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
}
