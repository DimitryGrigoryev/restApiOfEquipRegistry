package org.example.repository.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToOne
    private EquipType equipType;
    @Column
    private String country;
    @Column
    private String company;
    @Column
    @JoinColumn(name = "order_online", nullable = false)
    private boolean orderOnline;
    @Column
    @JoinColumn(name = "in_credit", nullable = false)
    private boolean inCredit;

    @OneToMany(mappedBy = "equipment",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> model;

}
