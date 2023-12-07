package com.rentacar.rentaacar.services.dtos.requests.Maintenance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMaintenanceRequest {
    @NotBlank(message = "Bakım tarihi boş olamaz.")
    @NotNull
    private LocalDate maintenanceDate;

    @NotBlank(message = "Bakım türü boş olamaz.")
    @NotNull
    @Size(min = 2, message = "Bakım türü 2 karakterden az olamaz.")
    private String maintenanceType;

    @NotBlank(message = "Bakımın fiyatı boş olamaz.")
    @NotNull
    private Double cost;

    @NotBlank(message = "Mekanik bilgisi boş olamaz.")
    @NotNull
    private String mechanic;

    @NotBlank(message = "Sonraki bakım tarihi boş olamaz.")
    @NotNull
    private LocalDate nextMaintenanceDate;

    @NotBlank(message = "Bakım açıklaması boş olamaz.")
    @NotNull
    private String description;
}