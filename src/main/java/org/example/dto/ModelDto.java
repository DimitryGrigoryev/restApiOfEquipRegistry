package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder(builderMethodName = "builderModelDto")
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
