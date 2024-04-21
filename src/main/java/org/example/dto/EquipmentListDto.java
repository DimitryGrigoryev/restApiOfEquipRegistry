package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;


public class EquipmentListDto extends EquipmentNameDto{

    @NotEmpty
    @JsonProperty("equip_type_code")
    private String equipTypeCode;
    private String country;
    private String company;
    @JsonProperty("order_online")
    private boolean orderOnline;
    @JsonProperty("in_credit")
    private boolean inCredit;


    public EquipmentListDto() {
    }

    public EquipmentListDto(String name, String equipTypeCode, String country, String company, boolean orderOnline, boolean inCredit) {
        super(name);
        this.equipTypeCode = equipTypeCode;
        this.country = country;
        this.company = company;
        this.orderOnline = orderOnline;
        this.inCredit = inCredit;
    }

    public String getEquipTypeCode() {
        return equipTypeCode;
    }

    public void setEquipTypeCode(String equipTypeCode) {
        this.equipTypeCode = equipTypeCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isOrderOnline() {
        return orderOnline;
    }

    public void setOrderOnline(boolean orderOnline) {
        this.orderOnline = orderOnline;
    }

    public boolean isInCredit() {
        return inCredit;
    }

    public void setInCredit(boolean inCredit) {
        this.inCredit = inCredit;
    }

}
