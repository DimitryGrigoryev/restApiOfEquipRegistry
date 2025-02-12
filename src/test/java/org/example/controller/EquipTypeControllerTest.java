package org.example.controller;

import org.example.dto.EquipTypeDto;
import org.example.service.EquipTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Test class for the {@link EquipTypeController}
 */
@ExtendWith(MockitoExtension.class)
public class EquipTypeControllerTest {


    @Mock
    private EquipTypeService equipTypeService;

    @InjectMocks
    private EquipTypeController equipTypeController;

    @Test
    public void fetchAllTypesEquip() {
        // Arrange
        List<EquipTypeDto> expected = Arrays.asList(
                new EquipTypeDto("testName1"),
                new EquipTypeDto("testName2")
        );
        when(equipTypeService.findAll(Pageable.unpaged())).thenReturn(expected);

        // Act
        ResponseEntity<List<EquipTypeDto>> response = equipTypeController.fetchAllTypesEquip(Pageable.unpaged());

        // Assert
        assertEquals(expected, response.getBody());
        verify(equipTypeService, times(1)).findAll(Pageable.unpaged());
    }


    @Test
    @DisplayName("testEquipTypesByName_test")
    public void testEquipTypesByName() {

        // Arrange
        String name = "testName";
        EquipTypeDto expected = new EquipTypeDto("тест название");
        when(equipTypeService.findByNameIgnoreCase(name)).thenReturn(expected);

        // Act
        ResponseEntity<EquipTypeDto> response = equipTypeController.equipTypesByName(name);

        // Assert
        assertEquals(expected, response.getBody());
        verify(equipTypeService, times(1)).findByNameIgnoreCase(name);
    }
}