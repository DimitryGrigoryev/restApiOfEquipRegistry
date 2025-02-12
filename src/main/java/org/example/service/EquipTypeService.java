package org.example.service;

import org.example.dto.EquipTypeDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EquipTypeService {
    List<EquipTypeDto> findAll(Pageable pageable);
    EquipTypeDto findByNameIgnoreCase(String name);
}
