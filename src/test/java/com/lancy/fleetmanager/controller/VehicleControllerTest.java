package com.lancy.fleetmanager.controller;

import com.lancy.fleetmanager.model.Vehicle;
import com.lancy.fleetmanager.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
@ActiveProfiles("test")
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // This mocks the DB so the test stays isolated
    private VehicleRepository repository;

    @Test
    void shouldReturnAllVehicles() throws Exception {
        // Mocking the database response
        when(repository.findAll()).thenReturn(List.of(
                new Vehicle("V1", "Tesla Model 3", "ACTIVE", 88.5)
        ));

        mockMvc.perform(get("/api/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("V1"))
                .andExpect(jsonPath("$[0].model").value("Tesla Model 3"))
                .andExpect(jsonPath("$[0].status").value("ACTIVE")) // Added [0]
                .andExpect(jsonPath("$[0].batteryLevel").value(88.5)); // Added [0]

    }
    @Test
    void shouldReturnVehicleById_WhenFound() throws Exception {
        // Mocking: Repository returns a valid Entity
        com.lancy.fleetmanager.model.Vehicle entity =
                new com.lancy.fleetmanager.model.Vehicle("V1", "Tesla Model 3", "ACTIVE", 88.5);

        when(repository.findById("V1")).thenReturn(Optional.of(entity));

        mockMvc.perform(get("/api/vehicles/V1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("V1"))
                .andExpect(jsonPath("$.model").value("Tesla Model 3"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.batteryLevel").value(88.5));
    }

    @Test
    void shouldReturn404_WhenVehicleNotFound() throws Exception {
        // Mocking: Repository returns empty for a missing ID
        when(repository.findById("NON-EXISTENT")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/vehicles/NON-EXISTENT"))
                .andExpect(status().isNotFound()) // Verifies 404 Status
                .andExpect(jsonPath("$.message").value("Vehicle not found with ID: NON-EXISTENT"))
                .andExpect(jsonPath("$.timestamp").exists()); // Verifies GlobalExceptionHandler format
    }
}
