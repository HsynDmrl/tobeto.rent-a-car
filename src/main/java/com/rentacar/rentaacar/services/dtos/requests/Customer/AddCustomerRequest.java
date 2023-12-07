package com.rentacar.rentaacar.services.dtos.requests.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {

    @NotBlank(message = "Müşteri adı boş olamaz.")
    @NotNull
    @Size(min = 3, max = 50, message = "Müşteri Adı 3 ile 50 hane arasında olmalıdır.")
    private String name;

    @NotBlank(message = "Müşteri Soyadı boş olamaz.")
    @NotNull
    @Size(min = 2, max = 50, message = "Müşteri Soyadı 3 ile 50 hane arasında olmalıdır.")
    private String surname;

    @NotBlank(message = "Müşterinin adresi boş olamaz.")
    @NotNull
    private String address;

    @NotBlank(message = "Müşteri Şehri boş olamaz.")
    @NotNull
    private String city;

    @Email
    @NotBlank(message = "Müşteri email adresi boş olamaz.")
    @NotNull
    private String email;

    @NotBlank(message = "Müşteri telefon numarası boş olamaz.")
    @NotNull
    @Size(min = 11, max = 11, message = "Müşteri telefon numarası 11 haneli olmalıdır.")
    private String phone;

    @NotBlank
    @NotNull
    @Size(min = 6, max = 9, message = "Müşteri ehliyet numarası 7 yada 8 haneli olmalıdır.")
    private String licenseNumber;

    @NotBlank
    @NotNull
    @Size(min = 11, max = 11, message = "Müşteri TC numarası 11 haneli olmalıdır.")
    private String tcNo;
}
