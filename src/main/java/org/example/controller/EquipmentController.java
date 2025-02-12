package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.example.configuration.ErrorControllerAdvice;
import org.example.dto.EquipmentDto;
import org.example.dto.EquipmentListDto;
import org.example.dto.EquipmentNameDto;
import org.example.service.EquipmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "registry/equipment", produces = APPLICATION_JSON_VALUE)
@Tag(name = "api.registry.tag.name", description = "api.registry.tag.description")
public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @Operation(summary = "api.registry.equipment.equipment.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.registry.equipment.by-name.api-responses.200.description"),
            @ApiResponse(responseCode = "404", description = "api.registry.equipment.by-name.api-responses.404.description", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "api.registry.server.error", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorControllerAdvice.ErrorResponse.class))})
    })
    @GetMapping(value = "/find-by-name-with-available-model/{name}")
    public ResponseEntity<EquipmentDto> equipmentByNameWithAvailableModel(@PathVariable @Size(min = 3, max = 255) String name) {
        return ResponseEntity.ok(equipmentService.findByNameWithAvailableModel(name));
    }
    @GetMapping(value = "/find-by-name/{name}")
    public ResponseEntity<EquipmentDto> equipmentByName(@PathVariable @Size(min = 3, max = 255) String name) {
        return ResponseEntity.ok(equipmentService.findByNameIgnoreCase(name));
    }

    @GetMapping(value = "/by-equip-type/{equipType}") // pageable
    public ResponseEntity<List<EquipmentListDto>> equipmentsByEquipType(@PathVariable @Size(min = 3, max = 255) String equipType, Pageable pageable) {
        return ResponseEntity.ok(equipmentService.findByEquipType(equipType, pageable));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<EquipmentNameDto> create(@Valid @RequestBody EquipmentListDto equipment){
        return ResponseEntity.ok(equipmentService.create(equipment));
    }

    @DeleteMapping(value = "{name}")
    public ResponseEntity<Void> delete(@PathVariable @Size(min = 3,max = 255) String name) {
        this.equipmentService.delete(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<EquipmentListDto>> findAllEquipments(Pageable pageable) {
        return ResponseEntity.ok(this.equipmentService.findAllEquip(pageable));
    }

}
