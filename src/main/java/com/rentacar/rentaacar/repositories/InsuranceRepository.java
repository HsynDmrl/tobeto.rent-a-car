package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Integer> {

    boolean existsBypolicyNumber(String policyNumber);

    // Derived sorgu 1: Sigorta şirketi ismi verilen sigortaları getir
    List<Insurance> findByInsuranceCompany(String insuranceCompany);

    // Derived sorgu 2: Belirli bir poliçe numarasına sahip olmayan sigortaları getir
    List<Insurance> findByPolicyNumberIsNot(String policyNumber);

    // JPQL sorgu 1: Belirli bir kapsam türüne sahip sigortaları getir
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse(i.insuranceCompany, i.policyNumber, i.expirationDate, i.coverageType, i.premiumAmount, i.status) FROM Insurance i WHERE i.coverageType = :coverageType")
    List<GetInsuranceListResponse> getInsurancesByCoverageType(String coverageType);

    // JPQL sorgu 2: Belirli bir prim miktarından büyük olan sigortaları getir
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse(i.insuranceCompany, i.policyNumber, i.expirationDate, i.coverageType, i.premiumAmount, i.status) FROM Insurance i WHERE i.premiumAmount > :premiumAmount")
    List<GetInsuranceListResponse> getInsurancesWithPremiumGreaterThan(String premiumAmount);

}
