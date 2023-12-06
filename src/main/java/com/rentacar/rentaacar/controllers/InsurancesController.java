package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.services.abstracts.InsuranceService;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.AddInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.UpdateInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/insurances")
@AllArgsConstructor
public class InsurancesController {
    private final InsuranceService insuranceService;
    @GetMapping
    public List<GetInsuranceListResponse> getAll() {
        return this.insuranceService.getAll();
    }
    @GetMapping("{id}")
    public GetInsuranceResponse getById(@PathVariable int id){
        return this.insuranceService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddInsuranceRequest addInsuranceDto){
        insuranceService.add(addInsuranceDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateInsuranceRequest updateInsuranceDto){
        insuranceService.update(id, updateInsuranceDto);
    }
    @GetMapping("/findByInsuranceCompany")
    List<GetInsuranceListResponse> findByInsuranceCompany(String insuranceCompany)
    {
        return insuranceService.findByInsuranceCompany(insuranceCompany);
    }
    @GetMapping("/findByPolicyNumberIsNot")
    List<GetInsuranceListResponse> findByPolicyNumberIsNot(String policyNumber)
    {
        return insuranceService.findByPolicyNumberIsNot(policyNumber);
    }
    @GetMapping("/getInsurancesByCoverageType")
    List<GetInsuranceListResponse> getInsurancesByCoverageType(String coverageType) {
        return insuranceService.getInsurancesByCoverageType(coverageType);
    }
    @GetMapping("getInsurancesWithPremiumGreaterThan")
    List<GetInsuranceListResponse> getInsurancesWithPremiumGreaterThan(String premiumAmount) {
        return insuranceService.getInsurancesWithPremiumGreaterThan(premiumAmount);
    }
}
