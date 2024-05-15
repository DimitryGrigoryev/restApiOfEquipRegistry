package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EquipmentListDto extends EquipmentNameDto{

    @NotEmpty
    @JsonProperty("equip_type_code")
    String equipTypeCode;
    String country;
    String company;
    @JsonProperty("order_online")
    boolean orderOnline;
    @JsonProperty("in_credit")
    boolean inCredit;

    public EquipmentListDto(String name, String equipTypeCode, String country, String company, boolean orderOnline, boolean inCredit) {
        super(name);
        this.equipTypeCode = equipTypeCode;
        this.country = country;
        this.company = company;
        this.orderOnline = orderOnline;
        this.inCredit = inCredit;
    }


}
