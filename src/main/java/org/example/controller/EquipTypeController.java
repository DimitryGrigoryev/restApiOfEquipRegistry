package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.dto.EquipTypeDto;
import org.example.service.EquipTypeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "registry/equip-types", produces = APPLICATION_JSON_VALUE)
@Tag(name = "api.registry.tag.name", description = "api.registry.tag.description")
public class EquipTypeController {
    private final EquipTypeService equipTypeService;

    public EquipTypeController(@Qualifier("jpaEquipTypeService") EquipTypeService equipTypeService) {
        this.equipTypeService = equipTypeService;
    }

    @GetMapping
    public ResponseEntity<List<EquipTypeDto>> fetchAllTypesEquip(){
        return ResponseEntity.ok(this.equipTypeService.findAll());
    }
}
