package com.lancy.fleetmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    private String id;
    private String model;
    private String status;
    private double batteryLevel;

    public Vehicle() {}

    public Vehicle(String id, String model, String status, double batteryLevel) {
        this.id = id;
        this.model = model;
        this.status = status;
        this.batteryLevel = batteryLevel;
    }

    // Getters
    public String getId() { return id; }
    public String getModel() { return model; }
    public String getStatus() { return status; }
    public double getBatteryLevel() { return batteryLevel; }

    // Setters (Crucial for DB Updates)
    public void setId(String id) { this.id = id; }
    public void setModel(String model) { this.model = model; }
    public void setStatus(String status) { this.status = status; }
    public void setBatteryLevel(double batteryLevel) { this.batteryLevel = batteryLevel; }
}
