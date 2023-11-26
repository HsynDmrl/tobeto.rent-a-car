package com.rentacar.rentaacar.services.dtos.responses.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetVehicleResponse {
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
}