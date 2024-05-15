package org.example.repository.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "options")
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String name;
    @Column
    String description;
    @ManyToOne
    @JoinColumn(name = "model_id",nullable = false)
    Model modelId;

    public Options(String name, String description, Model modelId) {
        this.name = name;
        this.description = description;
        this.modelId = modelId;
    }

}
