package com.rentacar.rentaacar.services.dtos.requests.Vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddVehicleRequest {

    @NotBlank(message = "Marka adı boş olamaz.")
    @NotNull
    @Size(min = 2, max = 50, message = "Marka bilgisi 2 veya 50 karakterden oluşmalı.")
    private String brand;

    @NotBlank(message = "Model bilgisi boş olamaz.")
    @NotNull
    @Size(min = 2, max = 50, message = "Model bilgisi 2 veya 50 karakterden oluşmalı.")
    private String model;

    @NotBlank(message = "Yıl bilgisi boş olamaz.")
    @NotNull
    @Size(min = 4, max = 4, message = "Yıl bilgisi 4 karakterden oluşmalı.")
    private int year;

    @NotBlank(message = "Renk bilgisi boş olamaz.")
    @NotNull
    @Size(min = 2, message = "Renk bilgisi geçersiz. En az 2 karakterden oluşmalıdır.")
    private String color;

    @NotBlank(message = "Günlük kira bedeli geçersiz.")
    @NotNull
    private double dailyCost;

    @NotBlank(message = "Durum bilgisi boş olamaz.")
    @NotNull
    private String status;

    @NotBlank(message = "Plaka numarası boş olamaz.")
    @NotNull
    @Size(min = 7, max = 9, message = "Plaka numarası 7 ila 9 karakterler arasında oluşmalıdır.")
    private String plateNumber;

    @NotBlank(message = "Araç kilometre bilgisi geçerli değil.")
    @NotNull
    private int odometer;

    @NotBlank(message = "Yakıt türü bilgisi geçerli değil.")
    @NotNull
    private String fuelType;

    @NotBlank(message = "Notlar bilgisi geçerli değil.")
    @NotNull
    private String notes;

    @NotBlank(message = "Sigorta ID geçersiz.")
    @NotNull
    private int insoranceId;

    @NotBlank(message = "Bakım kaydı ID boş olamaz.")
    @NotNull
    private int maintenanceId;

}