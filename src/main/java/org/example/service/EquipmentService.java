package org.example.service;

import org.example.dto.EquipmentDto;
import org.example.dto.EquipmentListDto;
import org.example.dto.EquipmentNameDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EquipmentService {
    EquipmentNameDto create(EquipmentListDto equipment);
    void delete (String name);
    List<EquipmentListDto> findAllEquip(Pageable pageable);
    EquipmentDto findByNameIgnoreCase(String name);
    EquipmentDto findByNameWithAvailableModel(String name);
    List<EquipmentListDto> findByEquipType(String equipType, Pageable pageable);
}
