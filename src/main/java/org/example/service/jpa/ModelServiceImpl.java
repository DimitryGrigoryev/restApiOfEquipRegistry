package org.example.service.jpa;

import org.example.dto.ModelDto;
import org.example.dto.ModelNameDto;
import org.example.dto.OptionsDto;
import org.example.exception.EquipmentNotFoundException;
import org.example.exception.ModelNotFoundException;
import org.example.repository.EquipmentRepository;
import org.example.repository.ModelRepository;
import org.example.repository.entity.Equipment;
import org.example.repository.entity.Model;
import org.example.repository.entity.Options;
import org.example.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("jpaModelService")
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final EquipmentRepository equipmentRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, EquipmentRepository equipmentRepository) {
        this.modelRepository = modelRepository;
        this.equipmentRepository = equipmentRepository;
    }



    @Override
    @Transactional
    public ModelNameDto create(ModelDto dto) {
        Equipment equipment = this.equipmentRepository.findById(dto.getEquipmentName())
                .orElseThrow(EquipmentNotFoundException::new);
        Model model = new Model();
        model.setName(dto.getName());
        model.setEquipment(equipment);
        model.setSerialNum(dto.getSerialNum());
        model.setColor(dto.getColor());
        model.setSize(dto.getSize());
        model.setPrice(dto.getPrice());
        model.setOptions(dto.getOptions()
                .stream()
                .map(OptionsDto -> new Options(OptionsDto.getName(), OptionsDto.getDescription(), model)).toList());
        model.setAvailable(dto.isAvailable());
        String name = this.modelRepository.save(model).getName();
        return new ModelNameDto(name);
    }

    @Override
    @Transactional
    public void delete(String name) {
        Model model = this.modelRepository.findById(name).orElseThrow(ModelNotFoundException::new);
        this.modelRepository.deleteById(model.getName());
    }


    @Override
    public List<ModelDto> findAll() {
        return this.modelRepository.findAll()
                .stream()
                .map(model -> new ModelDto(model.getName(), model.getEquipment().getName(), model.getSerialNum(), model.getColor(), model.getSize(), model.getPrice(), model.getOptions()
                        .stream()
                        .map(options -> new OptionsDto(options.getName(), options.getDescription())).collect(Collectors.toSet()),
                        model.isAvailable()))
                .toList();
    }

    @Override
    public ModelDto findByName(String name) {
        return this.modelRepository.findById(name)
                .map(model -> new ModelDto(model.getName(), model.getEquipment().getName(), model.getSerialNum(), model.getColor(), model.getSize(), model.getPrice(),
                        model.getOptions()
                        .stream()
                        .map(options -> new OptionsDto(options.getName(), options.getDescription())).collect(Collectors.toSet()), model.isAvailable())).orElseThrow(ModelNotFoundException::new);
    }

    @Override
    public List<ModelDto> findByEquipmentName(String equipName) {
        List<Model> models = this.modelRepository.findByEquipment_Name(equipName);
        if (models.isEmpty()) {
            throw new ModelNotFoundException();
        }
        return models
                .stream()
                .map(model -> new ModelDto(model.getName(), model.getEquipment().getName(), model.getSerialNum(), model.getColor(), model.getSize(), model.getPrice(), model.getOptions()
                        .stream()
                        .map(options -> new OptionsDto(options.getName(), options.getDescription())).collect(Collectors.toSet()), model.isAvailable()))
                .toList();
    }
}
