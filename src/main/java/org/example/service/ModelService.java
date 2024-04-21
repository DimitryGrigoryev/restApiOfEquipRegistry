package org.example.service;

import org.example.dto.ModelDto;
import org.example.dto.ModelNameDto;

import java.util.List;

public interface ModelService {
    ModelNameDto create(ModelDto model);
    void delete (String name);
    List<ModelDto> find();
    ModelDto findByName(String name);
    List<ModelDto> findByEquipmentName (String equipmentName);

}
