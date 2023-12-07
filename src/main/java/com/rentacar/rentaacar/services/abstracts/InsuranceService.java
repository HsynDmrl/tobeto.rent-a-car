package com.rentacar.rentaacar.services.abstracts;

import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.AddInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.UpdateInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InsuranceService {
    public List<GetInsuranceListResponse> getAll();
    public GetInsuranceResponse getById(@PathVariable int id);
    public void add(AddInsuranceRequest addInsuranceDto);
    public void update(@PathVariable int id, @RequestBody UpdateInsuranceRequest updateInsuranceDto);
    public void delete(@PathVariable int id, String areYouSure);
    List<GetInsuranceListResponse> findByInsuranceCompany(String insuranceCompany);
    List<GetInsuranceListResponse> findByPolicyNumberIsNot(String policyNumber);
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse(i.insuranceCompany, i.policyNumber, i.expirationDate, i.coverageType, i.premiumAmount, i.status) FROM Insurance i WHERE i.coverageType = :coverageType")
    List<GetInsuranceListResponse> getInsurancesByCoverageType(String coverageType);
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse(i.insuranceCompany, i.policyNumber, i.expirationDate, i.coverageType, i.premiumAmount, i.status) FROM Insurance i WHERE i.premiumAmount > :premiumAmount")
    List<GetInsuranceListResponse> getInsurancesWithPremiumGreaterThan(String premiumAmount);

}
