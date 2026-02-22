package com.lancy.fleetmanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllVehicles() throws Exception {
        mockMvc.perform(get("/api/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].model").value("Tesla Model 3"));
    }

    @Test
    void shouldReturnVehicleById() throws Exception {
        mockMvc.perform(get("/api/vehicles/V1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("V1"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }
}
