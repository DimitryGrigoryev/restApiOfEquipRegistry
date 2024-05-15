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
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String name;
    @ManyToOne
    EquipType equipType;
    @Column
    String country;
    @Column
    String company;
    @Column
    boolean order_online;
    @Column
    boolean in_credit;

    @OneToMany(mappedBy = "equipmentId",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Model> model;

}
