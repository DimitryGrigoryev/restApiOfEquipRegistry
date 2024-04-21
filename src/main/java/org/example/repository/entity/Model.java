package org.example.repository.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Model {

    @Id
    private String name;

    @ManyToOne
    @JoinColumn(name = "equipment_name", nullable = false)
    private Equipment equipment;
    @Column
    @JoinColumn(name = "serial_num", nullable = false)
    private String serialNum;
    @Column
    private String color;
    @Column
    private int size;
    @Column
    private double price;
    @Column
    private boolean available;

    @OneToMany(mappedBy = "model", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Options> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }
}
