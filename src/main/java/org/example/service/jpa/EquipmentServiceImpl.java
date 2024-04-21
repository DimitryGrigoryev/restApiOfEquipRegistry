 package org.example.service.jpa;

import org.example.dto.*;
import org.example.exception.EquipTypeNotFoundException;
import org.example.exception.EquipmentNotFoundException;
import org.example.repository.EquipTypeRepository;
import org.example.repository.EquipmentRepository;
import org.example.repository.entity.EquipType;
import org.example.repository.entity.Equipment;
import org.example.repository.entity.Model;
import org.example.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

 @Service("jpaEquipmentService")
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipTypeRepository equipTypeRepository;
    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipTypeRepository equipTypeRepository) {
        this.equipmentRepository = equipmentRepository;
         this.equipTypeRepository = equipTypeRepository;
    }
    @Override
    @Transactional
    public EquipmentNameDto create(EquipmentListDto dto) {
        EquipType equipType = this.equipTypeRepository.findById(dto.getEquipTypeCode())
                .orElseThrow(EquipTypeNotFoundException::new);
        Equipment equipment = new Equipment();

        equipment.setName(dto.getName());
        equipment.setEquipType(equipType);
        equipment.setCountry(dto.getCountry());
        equipment.setCompany(dto.getCompany());
        equipment.setOrder_online(dto.isOrderOnline());
        equipment.setIn_credit(dto.isInCredit());
        String name = this.equipmentRepository.save(equipment).getName();
        return new EquipmentNameDto(name);
    }

    @Override
    @Transactional
    public void delete(String name) {
            Equipment equip = this.equipmentRepository.findById(name).orElseThrow(EquipmentNotFoundException::new);
            this.equipmentRepository.deleteById(equip.getName());
    }

    @Override
    public List<EquipmentListDto> find() {
        return this.equipmentRepository.findAll()
                .stream()
                .map(equipment -> new EquipmentListDto(equipment.getName(), equipment.getEquipType().getName(), equipment.getCountry(), equipment.getCompany(), equipment.isOrder_online(), equipment.isIn_credit()))
                .toList();
    }


    @Override
    public EquipmentDto findByName(String name) {
        return this.equipmentRepository.findById(name)
                .map(equipment -> new EquipmentDto(equipment.getName(), equipment.getEquipType().getName(), equipment.getCountry(), equipment.getCompany(), equipment.isOrder_online(), equipment.isIn_credit(),
                        equipment.getModel().stream().filter(Model::isAvailable)
                                .map(model -> new AvailModelDto(model.getName())).collect(Collectors.toSet())
                        )
                ).orElseThrow(EquipmentNotFoundException::new);
    }

    @Override
    public List<EquipmentListDto> findByEquipType(String equipType) {
        List<Equipment> equipments = this.equipmentRepository.findByEquipType_Code(equipType);
        if (equipments.isEmpty()) {
            throw new EquipmentNotFoundException();
        }
        return equipments
                .stream()
                .map(equipment ->
                    new EquipmentListDto(equipment.getName(), equipment.getEquipType().getName(), equipment.getCountry(), equipment.getCompany(), equipment.isOrder_online(), equipment.isIn_credit()))
                .toList();
    }
}
