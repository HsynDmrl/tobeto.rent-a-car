package com.rentacar.rentaacar.services.dtos.requests.Insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInsuranceRequest {
    private int vehicleId;
    private String insuranceCompany;
    private String policyNumber;
    private String expirationDate;
    private String coverageType;
    private String premiumAmount;
    private String status;
}