package org.example.service;

import org.example.dto.EquipmentDto;
import org.example.dto.EquipmentListDto;
import org.example.dto.EquipmentNameDto;

import java.util.List;

public interface EquipmentService {
    EquipmentNameDto create(EquipmentListDto equipment);
    void delete (String name);
    List<EquipmentListDto> find();
    EquipmentDto findByName (String name);
    List<EquipmentListDto> findByEquipType(String equipType);

}
