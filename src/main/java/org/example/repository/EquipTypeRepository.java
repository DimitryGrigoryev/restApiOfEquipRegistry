package org.example.repository;

import org.example.dto.EquipTypeDto;
import org.example.repository.entity.EquipType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipTypeRepository extends JpaRepository<EquipType, String> {
    EquipTypeDto findByNameIgnoreCase(String name);
}
