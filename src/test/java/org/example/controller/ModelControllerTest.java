package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.ModelDto;
import org.example.dto.ModelNameDto;
import org.example.repository.ModelRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link ModelController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModelRepository modelRepository;

    @Test
    @DisplayName("find_all_models_test")
    public void find_all_models_test() throws Exception {
        mockMvc.perform(get("/registry/model")
                        .param("page", "0")
                        .param("size", "3")
                        .param("sort", ""))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("model_by_name_test")
    public void model_by_name_test() throws Exception {
        mockMvc.perform(get("/registry/model/{0}", "SamSL-402"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("models_by_equip_name_test")
    public void models_by_equip_name_test() throws Exception {
        mockMvc.perform(get("/registry/model/by-equipment-name/{0}", "Смартфон Panasonic")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("models_by_equip_type_name_test")
    public void models_by_equip_type_name_test() throws Exception {
        mockMvc.perform(get("/registry/model/by-equip-type-name/{0}", "Телевизоры")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sort", ""))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("models_by_price_between_from_to_test")
    public void models_by_price_between_from_to_test() throws Exception {
        mockMvc.perform(get("/registry/model/by-price-between/{0}/{1}", "5", "100")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("models_all_price_test")
    public void models_all_price_test() throws Exception {
        mockMvc.perform(get("/registry/model/by-all-price")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("models_by_color_test")
    public void models_by_color_test() throws Exception {
        mockMvc.perform(get("/registry/model/by-color/{0}", "blue")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sort", "desc"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("models_all_colors_test")
    public void models_all_colors_test() throws Exception {
        mockMvc.perform(get("/registry/model/by-all-color")
                        .param("page", "0")
                        .param("size", "2"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("find_models_by_equipment_id_name_and_availableIs_test")
    public void find_models_by_equipment_id_name_and_availableIs_test() throws Exception {
        mockMvc.perform(get("/registry/model/by-equipment-name-is-avail/{0}", "Компьютер Samsung"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("create_model_test")
    public void create_model_test() throws Exception {
        String model = """
                {
                  "name": "SL-404102",
                  "color": "white",
                  "size": 59,
                  "price": 100.0,
                  "available": false,
                  "options": [
                    {
                      "name": "Category",
                      "description": "Home"
                    },
                    {
                      "name": "TypeProcessor",
                      "description": "Dual"
                    }
                  ],
                  "equipment_name": "Смартфон Samsung1",
                  "serial_num": "L2321231212"
                }""";

        ModelDto modelDto = objectMapper.readValue(model, ModelDto.class);
        ModelNameDto expectedModelNameDto = new ModelNameDto(modelDto.getName());

        when(modelRepository.save(Mockito.any())).thenReturn(expectedModelNameDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/registry/model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modelDto)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("delete_model_test")
    public void delete_model_test() throws Exception {
        mockMvc.perform(delete("/registry/model/{0}", "SL-404102"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
