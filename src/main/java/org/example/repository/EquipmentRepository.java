package org.example.repository;

import org.example.repository.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    List<Equipment> findByEquipType_Code(String equipType);

    Equipment findByName(String name);

}
