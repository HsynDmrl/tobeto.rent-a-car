package com.rentacar.rentaacar.services.dtos.requests.Maintenance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMaintenanceRequest {
    private LocalDate maintenanceDate;
    private String maintenanceType;
    private Double cost;
    private String mechanic;
    private LocalDate nextMaintenanceDate;
    private String description;
}