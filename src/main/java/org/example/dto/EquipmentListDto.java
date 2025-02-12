package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentListDto extends EquipmentNameDto{

    @NotEmpty
    @JsonProperty("equip_type_id")
    private String equipTypeId;
    private String country;
    private String company;
    @JsonProperty("order_online")
    private boolean orderOnline;
    @JsonProperty("in_credit")
    private boolean inCredit;



}
