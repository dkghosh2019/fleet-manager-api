package com.lancy.fleetmanager.controller;

import com.lancy.fleetmanager.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        log.info("Fetching all fleet vehicles for monitoring");
        return List.of(
            new Vehicle("V1", "Tesla Model 3", "ACTIVE", 88.5),
            new Vehicle("V2", "Ford F-150 Lightning", "CHARGING", 42.0),
            new Vehicle("V3", "Rivian R1T", "MAINTENANCE", 15.2)
        );
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable String id) {
        log.info("Checking status for vehicle: {}", id);
        return new Vehicle(id, "Generic EV", "ACTIVE", 99.0);
    }
}
