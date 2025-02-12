package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotEmpty;
import org.example.dto.EquipmentDto;
import org.example.dto.EquipmentIncludeModelDto;
import org.example.dto.EquipmentListDto;
import org.example.dto.EquipmentNameDto;
import org.example.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the {@link EquipmentController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EquipmentControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int localServerPort;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentService equipmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testEquipmentByNameWithAvailableModel() throws Exception {
        String name = "testName";
        @NotEmpty Set<EquipmentIncludeModelDto> includeModels = Set.of(
                EquipmentIncludeModelDto.builder().name("ML_3031").build(),
                EquipmentIncludeModelDto.builder().name("ML_3030").build());
        @NotEmpty String equipTypeCode = "Tv";
        EquipmentDto equipmentDto = EquipmentDto.builder()
                .name(name)
                .includeModels(includeModels)
                .equipTypeId(equipTypeCode)
                .build();

        when(equipmentService.findByNameWithAvailableModel(name)).thenReturn(equipmentDto);

        mockMvc.perform(get("/registry/equipment/find-by-name-with-available-model/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(equipmentDto)))
                .andDo(print());
    }



    @Test
    public void equipmentByName_test() throws Exception {
        mockMvc.perform(get("/registry/equipment/find-by-name/{0}", "Смартфон Samsung"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void equipmentsByEquipType_test() throws Exception {
        mockMvc.perform(get("/registry/equipment/by-equip-type/{0}", "Смартфон")
                        .param("page", "0")
                        .param("size", "0")
                        .param("sort", ""))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("equipmentByName")
    public void equipmentByName() {
        String name = "Смартфон Samsung";

        ResponseEntity<EquipmentDto> getResponse = testRestTemplate.getForEntity(
                "http://localhost:" + localServerPort + "/registry/equipment/find-by-name/{name}",
                EquipmentDto.class,
                name
        );
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    }

    @Test
    @DisplayName("equipmentsByEquipType")
    public void equipmentsByEquipType(){
        String equipType = "Смартфоны";
        String page = "0";
        String size = "2";
        String sort = "";

        ResponseEntity<EquipmentListDto[]> getResponse = testRestTemplate.getForEntity(
                "http://localhost:" + localServerPort + "/registry/equipment/by-equip-type/{equipType}?page={page}&size={size}&sort={sort}",
                EquipmentListDto[].class,
                equipType,
                page,
                size,
                sort
        );
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    }

    @Test
    @DisplayName("create_equipment_test")
    public void create_equipment_test() throws Exception {
        EquipmentListDto equipment = EquipmentListDto.builder()
                .name("Смартфон Samsung1")
                .equipTypeId("4")
                .country("CHINA")
                .company("Samsung")
                .orderOnline(true)
                .inCredit(true)
                .build();
        EquipmentNameDto equipmentNameDto = new EquipmentNameDto(equipment.getName());

        when(equipmentService.create(equipment)).thenReturn(equipmentNameDto);

        mockMvc.perform(post("http://localhost:" + localServerPort + "/registry/equipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipment)))
                .andExpect(status().isOk())
                .andDo(print());
    }



    @Test
    @DisplayName("delete")
    public void delete() {
        String name = "Смартфон Samsung1";

        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(
                "http://localhost:" + localServerPort + "/registry/equipment/{name}",
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class,
                name
        );
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
    }

    @Test
    @DisplayName("findAllEquipments")
    public void findAllEquipments() {
        String page = "0";
        String size = "2";
        String sort = "";

        ResponseEntity<EquipmentListDto[]> getResponse = testRestTemplate.getForEntity(
                "http://localhost:" + localServerPort + "/registry/equipment?page={page}&size={size}&sort={sort}",
                EquipmentListDto[].class,
                page,
                size,
                sort
        );
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
    }
}
