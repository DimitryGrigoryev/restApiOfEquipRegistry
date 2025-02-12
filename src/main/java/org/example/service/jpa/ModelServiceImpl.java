package org.example.service.jpa;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ModelDto;
import org.example.dto.ModelNameDto;
import org.example.exception.EquipmentNotFoundException;
import org.example.exception.ModelNotFoundException;
import org.example.exception.ResponseDataIsNotValidException;
import org.example.repository.EquipmentRepository;
import org.example.repository.ModelRepository;
import org.example.repository.entity.Equipment;
import org.example.repository.entity.Model;
import org.example.service.ModelService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.utils.Mappers.*;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final EquipmentRepository equipmentRepository;

    public ModelServiceImpl(ModelRepository modelRepository, EquipmentRepository equipmentRepository) {
        this.modelRepository = modelRepository;
        this.equipmentRepository = equipmentRepository;
    }

    @Override
    @Transactional
    public ModelNameDto create(ModelDto modelDto) {
        log.info("Create model: {}", modelDto);
        Equipment equipment = this.equipmentRepository.findByName(modelDto.getEquipmentName());
        if (equipment == null) {
            throw new EquipmentNotFoundException();
        }

        final Model model = new Model();
        model.setName(modelDto.getName());
        model.setEquipment(equipment);
        model.setSerialNum(modelDto.getSerialNum());
        model.setColor(modelDto.getColor());
        model.setSize(modelDto.getSize());
        model.setPrice(modelDto.getPrice());
        model.setOptions(modelDto.getOptions().stream()
                .map(dto -> Pair.of(dto, model) )
                .map(TO_ENTITY_OPTIONS_FUNCTION)
                .toList());
        model.setAvailable(modelDto.isAvailable());

        return ModelNameDto.builder()
                .name(this.modelRepository.save(model).getName())
                .build();
    }

    @Override
    @Transactional
    public void delete(String name) {
        log.info("Delete model: {}", name);
        Model model = this.modelRepository.findByNameIgnoreCase(name);
        if (model == null) {
            throw new ModelNotFoundException();
        }
        this.modelRepository.delete(model);
    }

    @Override
    public List<ModelDto> findAll(Pageable pageable) {
        log.info("Find all model");
        return this.modelRepository.findAll(pageable).stream()
                .map(TO_MODEL_DTO_FUNCTION)
                .toList();
    }

    @Override
    public ModelDto findByNameIgnoreCase(String name) {
        log.info("Find model by name: {}", name);
        Model model = this.modelRepository.findByNameIgnoreCase(name);
        if (model == null) {
            log.debug("Model not found: {}", name);
            return null;
        }
        return this.modelRepository.findById(String.valueOf(model.getId()))
                .map(TO_MODEL_DTO_FUNCTION)
                .orElseThrow(ModelNotFoundException::new);
    }

    @Override
    public List<ModelDto> findByEquipmentName(String equipName, Pageable pageable) {
        log.info("Find by models by equipment name: {}", equipName);
        List<Model> models = this.modelRepository.findModelsByEquipmentId_Name(equipName, pageable);
        if (models.isEmpty()) {
            log.debug("Models not found: {}", equipName);
            return new ArrayList<>();
        }
        return models.stream()
                .map(TO_MODEL_DTO_FUNCTION)
                .toList();
    }

    @Override
    public List<ModelDto> findModelsByEquipmentId_NameAndAvailableIsTrue(String equipName) {
        log.info("Find by models by equipment name: {}", equipName);
        List<Model> models = this.modelRepository.findModelsByEquipmentId_NameAndAvailableIsTrue(equipName);
        if (models.isEmpty()) {
            log.debug("Models not found: {}", equipName);
            return new ArrayList<>();
        }
        return models.stream()
                .map(TO_MODEL_DTO_FUNCTION)
                .toList();
    }

    @Override // pageable
    public List<ModelDto> findByEquipTypeName(String equipTypeName, Pageable pageable) {
        log.info("Find models by equip type name: {}", equipTypeName);
        List<Equipment> equipments = this.equipmentRepository.findAllByEquipType_Name(equipTypeName);
        if (equipments.isEmpty()) {
            log.debug("Equipments not found: {}", equipTypeName);
            return new ArrayList<>();
        }
        return equipments.stream()
                .map(equipment -> this.modelRepository.findModelsByEquipmentId(equipment.getId()))
                .flatMap(List::stream)
                .map(TO_MODEL_DTO_FUNCTION)
                .toList();
    }

    @Override // pageable
    public List<ModelDto> findByColor(String color, Pageable pageable) {
        log.info("Find models by color: {}", color);
        List<String> allColor = this.findAllColor(pageable);
        if (!allColor.contains(color)) {
           log.debug("Color not found: {}", color);
           return new ArrayList<>();
        }
        return this.modelRepository.findModelsByColor(color)
                .stream()
                .map(TO_MODEL_DTO_FUNCTION)
                .toList();
    }

    @Override
    public List<String> findAllColor(Pageable pageable) {
        log.info("Find all color");
        List<Model> models = this.modelRepository.findAll(pageable).stream().toList();
        return models.stream()
                .map(Model::getColor)
                .distinct()
                .toList();
    }

    @Override
    public List<ModelDto> findByPriceBetweenFromTo(Double from, Double to, Pageable pageable) {
        log.info("Find models by price from to: {} - {}", from, to);
        double min = modelRepository.findFirstByPriceIsNotNullOrderByPriceAsc().getPrice();
        double max = modelRepository.findFirstByPriceIsNotNullOrderByPriceDesc().getPrice();
        if (from > to) throw new ResponseDataIsNotValidException();
        if (from < min) from = min;
        if (to > max) to = max;
        return this.modelRepository.findModelsByPriceBetweenOrderByPrice(from, to, pageable)
                .stream()
                .map(TO_MODEL_DTO_FUNCTION)
                .toList();
    }

    @Override
    public List<Double> findAllPrice(Pageable pageable) {
        log.info("Find all price");
        List<Model> models = this.modelRepository.findAll(pageable).stream().toList();
        return models.stream()
                .map(Model::getPrice)
                .collect(Collectors.toList());
    }
}
