package com.rentacar.rentaacar.dtos.responses.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerListResponse {
    private String name;
    private String surname;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String licenseNumber;
    private String tcNo;
}