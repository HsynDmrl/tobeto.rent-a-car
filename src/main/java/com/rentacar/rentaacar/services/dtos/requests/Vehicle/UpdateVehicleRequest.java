package com.rentacar.rentaacar.services.dtos.requests.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleRequest {
    private String brand;
    private String model;
    private int year;
    private String color;
    private double dailyCost;
    private String status;
    private String plateNumber;
    private int odometer;
    private String fuelType;
    private String notes;
    private int insoranceId;
    private int maintenanceId;
}