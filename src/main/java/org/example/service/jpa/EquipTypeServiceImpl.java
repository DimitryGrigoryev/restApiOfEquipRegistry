package org.example.service.jpa;

import org.example.dto.EquipTypeDto;
import org.example.exception.EquipmentNotFoundException;
import org.example.repository.EquipTypeRepository;
import org.example.repository.entity.EquipType;
import org.example.service.EquipTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jpaEquipTypeService")
public class EquipTypeServiceImpl implements EquipTypeService {

    private final EquipTypeRepository repository;

    @Autowired
    public EquipTypeServiceImpl(EquipTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EquipTypeDto> list() {
        List<EquipType> equipTypes = this.repository.findAll();
        if (equipTypes.isEmpty()) {
            throw new EquipmentNotFoundException();
        }
        return equipTypes.stream()
                .map(equipType -> new EquipTypeDto(equipType.getCode(), equipType.getName()))
                .toList();
    }
}
