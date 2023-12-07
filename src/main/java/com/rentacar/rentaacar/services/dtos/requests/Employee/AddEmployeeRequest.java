package com.rentacar.rentaacar.services.dtos.requests.Employee;

import jakarta.validation.constraints.Email;
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
public class AddEmployeeRequest {

    @NotBlank(message = "Çalışan adı boş olamaz.")
    @NotNull
    @Size(min = 3, max = 50, message = "Çalışan Adı 3 ile 50 hane arasında olmalıdır.")
    private String name;

    @NotBlank(message = "Çalışan Soyadı boş olamaz.")
    @NotNull
    @Size(min = 2, max = 50, message = "Çalışan Soyadı 3 ile 50 hane arasında olmalıdır.")
    private String surname;

    @NotBlank(message = "Çalışan Ünvanı boş olamaz.")
    @NotNull
    @Size(min = 2, max = 50, message = "Çalışan Ünvanı 3 ile 50 hane arasında olmalıdır.")
    private String title;

    @Email
    @NotBlank(message = "Çalışan email adresi boş olamaz.")
    @NotNull
    private String email;

    @NotBlank(message = "Çalışan telefon numarası boş olamaz.")
    @NotNull
    @Size(min = 11, max = 11, message = "Çalışan telefon numarası 11 haneli olmalıdır.")
    private String phone;

    @NotBlank(message = "Çalışanın işe giriş tarihi boş olamaz.")
    @NotNull
    private LocalDate hireDate;

    @NotBlank(message = "Çalışanın adresi boş olamaz.")
    @NotNull
    private String address;

    @NotBlank(message = "Çalışan Şehri boş olamaz.")
    @NotNull
    private String city;

    @NotBlank(message = "Çalışan maaşı boş olamaz.")
    @NotNull
    private double salary;

    @NotBlank
    @NotNull
    @Size(min = 11, max = 11, message = "Çalışan TC numarası 11 haneli olmalıdır.")
    private String tcNo;

}