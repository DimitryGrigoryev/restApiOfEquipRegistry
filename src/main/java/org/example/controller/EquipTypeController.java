package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import org.example.dto.EquipTypeDto;
import org.example.service.EquipTypeService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "registry/equip-types", produces = APPLICATION_JSON_VALUE)
@Tag(name = "api.registry.tag.name", description = "api.registry.tag.description")
public class EquipTypeController {
    private final EquipTypeService equipTypeService;

    public EquipTypeController( EquipTypeService equipTypeService) {
        this.equipTypeService = equipTypeService;
    }

    @GetMapping
    public ResponseEntity<List<EquipTypeDto>> fetchAllTypesEquip(Pageable pageable){
        return ResponseEntity.ok(this.equipTypeService.findAll(pageable));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<EquipTypeDto> equipTypesByName(@PathVariable @Size(min = 3, max = 255) String name){
        return ResponseEntity.ok(this.equipTypeService.findByNameIgnoreCase(name));
    }
}
