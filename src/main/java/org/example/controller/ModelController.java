package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.example.dto.ModelDto;
import org.example.dto.ModelNameDto;
import org.example.service.ModelService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "registry/model", produces = APPLICATION_JSON_VALUE)
@Tag(name = "api.registry.tag.name", description = "api.registry.tag.description")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<ModelDto>> findAllModels(Pageable pageable) {
        return ResponseEntity.ok(this.modelService.findAll(pageable));
    }

    @GetMapping(value = "{name}")
    public ResponseEntity<ModelDto> modelByName(@PathVariable @Size(min = 3, max = 255) String name) {
        return ResponseEntity.ok(modelService.findByNameIgnoreCase(name));
    }

    @GetMapping(value = "/by-equipment-name/{equipmentName}") // pageable
    public ResponseEntity<List<ModelDto>> modelsByEquipName(@PathVariable @Size(min = 3, max = 255) String equipmentName, Pageable pageable) {
        return ResponseEntity.ok(modelService.findByEquipmentName(equipmentName, pageable));
    }

    @GetMapping(value = "/by-equip-type-name/{equipTypeName}") // pageable
    public ResponseEntity<List<ModelDto>> modelsByEquipTypeName(@PathVariable @Size(min = 3, max = 255) String equipTypeName, Pageable pageable) {
        return ResponseEntity.ok(modelService.findByEquipTypeName(equipTypeName, pageable));
    }

    @GetMapping(value = "/by-price-between/{from}/{to}") // убрать в спецификацию , // pageable
    public ResponseEntity<List<ModelDto>> modelsByPriceBetweenFromTo(@PathVariable Double from, @PathVariable Double to, Pageable pageable) {
        return ResponseEntity.ok(modelService.findByPriceBetweenFromTo(from, to, pageable));
    }

    @GetMapping(value = "/by-all-price")
    public ResponseEntity<List<Double>> allPrice(Pageable pageable) {
        return ResponseEntity.ok(modelService.findAllPrice(pageable));
    }

    @GetMapping(value = "/by-color/{color}") // убрать в спецификацию
    public ResponseEntity<List<ModelDto>> modelsByColor(@PathVariable @Size(min = 3, max = 255) String color, Pageable pageable) {
        return ResponseEntity.ok(modelService.findByColor(color, pageable));
    }

    @GetMapping(value = "/by-all-color")
    public ResponseEntity<List<String>> allColors(Pageable pageable) {
        return ResponseEntity.ok(modelService.findAllColor(pageable));
    }

    @GetMapping(value = "/by-equipment-name-is-avail/{equipmentName}")
    public ResponseEntity<List<ModelDto>> findModelsByEquipmentId_NameAndAvailableIsTrue(@PathVariable @Size(min = 3, max = 255) String equipmentName) {
        return ResponseEntity.ok(modelService.findModelsByEquipmentId_NameAndAvailableIsTrue(equipmentName));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ModelNameDto> create(@Valid @RequestBody ModelDto model) {
        return  ResponseEntity.ok(modelService.create(model));
    }
    @DeleteMapping(value = "{name}")
    public ResponseEntity<Void> delete(@PathVariable @Size(min = 3, max = 255) String name) {
        this.modelService.delete(name);
        return ResponseEntity.ok().build();
    }
}
