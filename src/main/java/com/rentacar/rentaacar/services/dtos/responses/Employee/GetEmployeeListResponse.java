package com.rentacar.rentaacar.services.dtos.responses.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeListResponse {
    private String name;
    private String surname;
    private String title;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private String address;
    private String city;
    private double salary;
    private String tcNo;
}
