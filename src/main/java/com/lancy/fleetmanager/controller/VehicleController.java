package com.lancy.fleetmanager.controller;

import com.lancy.fleetmanager.exception.ResourceNotFoundException;
import com.lancy.fleetmanager.model.Vehicle;
import com.lancy.fleetmanager.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.support.Repositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleRepository repository;

    public VehicleController(VehicleRepository repository) {
        this.repository = repository;
    }



    @GetMapping
    public List<com.lancy.fleetmanager.dto.Vehicle> getAllVehicles() {
        log.info("Fetching all fleet vehicles from the database");

        // 1. Fetch Entities from DB
        List<com.lancy.fleetmanager.model.Vehicle> entities = repository.findAll();

        // 2. Map Entities to DTOs (Records)
        return entities.stream()
                .map(entity -> new com.lancy.fleetmanager.dto.Vehicle(
                        entity.getId(),
                        entity.getModel(),
                        entity.getStatus(),
                        entity.getBatteryLevel()))
                .toList();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable String id) {
        return repository.findById(id)
                .map(v -> new Vehicle(v.getId(), v.getModel(), v.getStatus(), v.getBatteryLevel()))
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + id));
    }


}
