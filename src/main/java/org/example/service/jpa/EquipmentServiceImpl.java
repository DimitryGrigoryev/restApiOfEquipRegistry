 package org.example.service.jpa;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.*;
import org.example.exception.EquipTypeNotFoundException;
import org.example.exception.EquipmentNotFoundException;
import org.example.repository.EquipTypeRepository;
import org.example.repository.EquipmentRepository;
import org.example.repository.ModelRepository;
import org.example.repository.entity.EquipType;
import org.example.repository.entity.Equipment;
import org.example.repository.entity.Model;
import org.example.service.EquipmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.utils.Mappers.*;

@Slf4j
@Service
@Transactional(readOnly = true)
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final EquipTypeRepository equipTypeRepository;
    private final ModelRepository modelRepository;

    public EquipmentServiceImpl(EquipmentRepository equipmentRepository,
                                EquipTypeRepository equipTypeRepository,
                                ModelRepository modelRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipTypeRepository = equipTypeRepository;
        this.modelRepository = modelRepository;
    }
    @Override
    @Transactional
    public EquipmentNameDto create(EquipmentListDto dto) {
        log.info("Create equipment: {}", dto);
        EquipType equipType = this.equipTypeRepository.findById(dto.getEquipTypeId())
                .orElseThrow(EquipTypeNotFoundException::new);
        Equipment equipment = Equipment.builder()
                .name(dto.getName())
                .equipType(equipType)
                .country(dto.getCountry())
                .company(dto.getCompany())
                .orderOnline(dto.isOrderOnline())
                .inCredit(dto.isInCredit())
                .build();
        return new EquipmentNameDto(this.equipmentRepository.save(equipment).getName());
    }

    @Override
    @Transactional
    public void delete(String name) {
        log.info("Delete equipment: {}", name);
        Long id = this.equipmentRepository.findByNameIgnoreCase(name).getId();
        Equipment equipment = this.equipmentRepository.findById(String.valueOf(id))
                .orElseThrow(EquipmentNotFoundException::new);
        this.equipmentRepository.delete(equipment);
    }

     @Override
     public EquipmentDto findByNameIgnoreCase(String name) {
        log.info("Find equipment by name: {}", name);
        String id = String.valueOf(this.equipmentRepository.findByNameIgnoreCase(name).getId());
        return this.equipmentRepository.findById(id)
                 .map(TO_EQUIPMENT_DTO_FUNCTION)
                 .orElseThrow(EquipmentNotFoundException::new);
     }

     @Override
     public EquipmentDto findByNameWithAvailableModel(String name) {
        log.info("Find equipment by name: {}", name);
        List<Model> models = this.modelRepository.findModelsByEquipmentId_NameAndAvailableIsTrue(name);
        return this.equipmentRepository.findById(String.valueOf(this.equipmentRepository.findByName(name).getId()))
                .map(equipment -> EquipmentDto.builder()
                        .name(equipment.getName())
                        .equipTypeId(equipment.getEquipType().getName())
                        .country(equipment.getCountry())
                        .company(equipment.getCompany())
                        .orderOnline(equipment.isOrderOnline())
                        .inCredit(equipment.isInCredit())
                        .includeModels(models.stream().map(TO_AVAIL_MODEL_DTO_FUNCTION).collect(Collectors.toSet()))
                        .build())
                 .orElseThrow(EquipmentNotFoundException::new);
    }

    @Override
    public List<EquipmentListDto> findByEquipType(String equipType, Pageable pageable) {
        log.info("Find equipment by equipType: {}", equipType);
        List<Equipment> equipments = this.equipmentRepository.findByEquipType_Name(equipType, pageable);
        return equipments.stream()
                .map(TO_EQUIPMENT_LIST_DTO_FUNCTION)
                .toList();
    }

    @Override
    public List<EquipmentListDto> findAllEquip(Pageable pageable) {
        log.info("Find all equipment");
        return this.equipmentRepository.findAll(pageable)
                .stream()
                .map(TO_EQUIPMENT_LIST_DTO_FUNCTION)
                .toList();
    }
}
