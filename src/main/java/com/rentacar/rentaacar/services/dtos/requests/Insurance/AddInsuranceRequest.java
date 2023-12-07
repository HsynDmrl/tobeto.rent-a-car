package com.rentacar.rentaacar.services.dtos.requests.Insurance;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInsuranceRequest {

    @NotBlank(message = "Sigorta şirketi boş olamaz.")
    @NotNull
    @Size(min = 2, message = "Sigorta şirketi 2 karakterden kısa olamaz.")
    private String insuranceCompany;

    @NotBlank(message = "Poliçe numarası boş olamaz.")
    @NotNull
    @Size(min = 3, message = "Poliçe numarası 2 karakterden kısa olamaz.")
    private String policyNumber;

    @NotBlank(message = "Son kullanma tarihi boş olamaz.")
    @NotNull
    private String expirationDate;

    @NotBlank(message = "Kapsam türü boş olamaz.")
    @NotNull
    @Size(min = 3, message = "Kapsam türü 2 karakterden kısa olamaz.")
    private String coverageType;

    @NotBlank(message = "Prim miktarı boş veya sıfır olamaz.")
    @NotNull
    private String premiumAmount;

    @NotBlank(message = "Durumu boş olamaz.")
    @NotNull
    private String status;

}