package org.example.repository;

import org.example.repository.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, String> {
    List<Model> findByEquipment_Name(String equipment);
}
