package org.example.service;

import org.example.dto.ModelDto;
import org.example.dto.ModelNameDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModelService {
    ModelNameDto create(ModelDto model);
    void delete(String name);
    List<ModelDto> findAll(Pageable pageable);
    ModelDto findByNameIgnoreCase(String name);
    List<ModelDto> findByEquipmentName(String equipmentName, Pageable pageable);
    List<ModelDto> findModelsByEquipmentId_NameAndAvailableIsTrue(String equipName);
    List<ModelDto> findByEquipTypeName(String equipTypeName, Pageable pageable);
    List<ModelDto> findByColor(String color, Pageable pageable);
    List<String> findAllColor(Pageable pageable);
    List<ModelDto> findByPriceBetweenFromTo(Double from, Double to, Pageable pageable);
    List<Double> findAllPrice(Pageable pageable);
}
