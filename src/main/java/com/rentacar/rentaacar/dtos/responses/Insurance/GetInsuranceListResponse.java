package com.rentacar.rentaacar.dtos.responses.Insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInsuranceListResponse {
    private String insuranceCompany;
    private String policyNumber;
    private String expirationDate;
    private String coverageType;
    private String premiumAmount;
    private String status;
}