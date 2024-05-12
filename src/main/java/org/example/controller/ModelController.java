package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.example.dto.ModelDto;
import org.example.dto.ModelNameDto;
import org.example.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    public ModelController(@Qualifier("jpaModelService")ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping(value = "{name}")
    public ResponseEntity<ModelDto> modelByName(@PathVariable @Size(min = 3, max = 255) String name) {
        return ResponseEntity.ok(modelService.findByName(name));
    }

    @GetMapping(value = "/by-equipment-name/{equipmentName}")
    public ResponseEntity<List<ModelDto>> modelsByEquipName(@PathVariable @Size(min = 3, max = 255) String equipmentName) {
        return ResponseEntity.ok(modelService.findByEquipmentName(equipmentName));
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

    @GetMapping
    public ResponseEntity<List<ModelDto>> fetchAllModels() {
        return ResponseEntity.ok(this.modelService.findAll());
    }
}
