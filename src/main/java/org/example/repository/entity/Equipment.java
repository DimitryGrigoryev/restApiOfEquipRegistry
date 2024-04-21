package org.example.repository.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Equipment {

    @Id
    private String name;
    @ManyToOne
    private EquipType equipType;
    @Column
    private String country;
    @Column
    private String company;
    @Column
    private boolean order_online;
    @Column
    private boolean in_credit;

    @OneToMany(mappedBy = "equipment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Model> model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EquipType getEquipType() {
        return equipType;
    }

    public void setEquipType(EquipType equipType) {
        this.equipType = equipType;
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

    public boolean isOrder_online() {
        return order_online;
    }

    public void setOrder_online(boolean order_online) {
        this.order_online = order_online;
    }

    public boolean isIn_credit() {
        return in_credit;
    }

    public void setIn_credit(boolean in_credit) {
        this.in_credit = in_credit;
    }

    public List<Model> getModel() {
        return model;
    }

    public void setModel(List<Model> model) {
        this.model = model;
    }
}
