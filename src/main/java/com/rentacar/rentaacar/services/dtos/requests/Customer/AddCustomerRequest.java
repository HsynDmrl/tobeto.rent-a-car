package com.rentacar.rentaacar.services.dtos.requests.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCustomerRequest {
    private String name;
    private String surname;
    private String address;
    private String city;
    private String email;
    private String phone;
    private String licenseNumber;
    private String tcNo;
}
