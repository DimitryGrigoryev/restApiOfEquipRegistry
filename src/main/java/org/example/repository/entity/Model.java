package org.example.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "model")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    Equipment equipmentId;
    @Column
    @JoinColumn(name = "serial_num", nullable = false)
    String serialNum;
    @Column
    String color;
    @Column
    int size;
    @Column
    double price;
    @Column
    boolean available;


    @OneToMany(mappedBy = "modelId",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Options> options;
}
