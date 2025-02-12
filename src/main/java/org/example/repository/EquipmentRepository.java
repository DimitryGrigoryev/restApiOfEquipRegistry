package org.example.repository;

import org.example.repository.entity.Equipment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, String> {
    List<Equipment> findByEquipType_Name(String equipType, Pageable pageable);
    Equipment findByName(String name);
    Equipment findByNameIgnoreCase(String name);

    List<Equipment> findAllByEquipType_Name(String equipTypeName);
}
