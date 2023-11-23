package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Insurance.AddInsuranceRequest;
import com.rentacar.rentaacar.dtos.requests.Insurance.UpdateInsuranceRequest;
import com.rentacar.rentaacar.dtos.responses.Insurance.GetInsuranceListResponse;
import com.rentacar.rentaacar.dtos.responses.Insurance.GetInsuranceResponse;
import com.rentacar.rentaacar.dtos.responses.Insurance.GetInsuranceListResponse;
import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.entities.Vehicle;
import com.rentacar.rentaacar.repositories.InsuranceRepository;
import com.rentacar.rentaacar.repositories.VehicleRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/insurances")
public class InsurancesController {
    private final InsuranceRepository insuranceRepository;

    private final VehicleRepository vehicleRepository;
    public InsurancesController(InsuranceRepository insuranceRepository,VehicleRepository vehicleRepository) {
        this.insuranceRepository = insuranceRepository;
        this.vehicleRepository = vehicleRepository;
    }
    @GetMapping
    public List<GetInsuranceListResponse> getAll() {
        List<Insurance> insuranceList = insuranceRepository.findAll();
        List<GetInsuranceListResponse> getInsuranceDtoList = new ArrayList<>();
        for (int i = 0; i < insuranceList.size(); i++) {
            Insurance insurance = insuranceList.get(i);
            GetInsuranceListResponse dto = new GetInsuranceListResponse();

            dto.setInsuranceCompany(insurance.getInsuranceCompany());
            dto.setPolicyNumber(insurance.getPolicyNumber());
            dto.setExpirationDate(String.valueOf(insurance.getExpirationDate()));
            dto.setCoverageType(insurance.getCoverageType());
            dto.setPremiumAmount(String.valueOf(insurance.getPremiumAmount()));
            dto.setStatus(insurance.getStatus());
            getInsuranceDtoList.add(dto);
        }
        return getInsuranceDtoList;
    }
    @GetMapping("{id}")
    public GetInsuranceResponse getById(@PathVariable int id){
        Insurance getByIdInsuranceDto = insuranceRepository.findById(id).orElseThrow();
        GetInsuranceResponse getByIdInsurance = new GetInsuranceResponse();
        getByIdInsurance.setInsuranceCompany(getByIdInsuranceDto.getInsuranceCompany());
        getByIdInsurance.setPolicyNumber(getByIdInsuranceDto.getPolicyNumber());
        getByIdInsurance.setExpirationDate(String.valueOf(getByIdInsuranceDto.getExpirationDate()));
        getByIdInsurance.setCoverageType(getByIdInsuranceDto.getCoverageType());
        getByIdInsurance.setPremiumAmount(String.valueOf(getByIdInsuranceDto.getPremiumAmount()));
        getByIdInsurance.setStatus(getByIdInsuranceDto.getStatus());

        return getByIdInsurance;
    }
    @PostMapping
    public void add(@RequestBody AddInsuranceRequest addInsuranceDto){
        Insurance addInsurance = new Insurance();
        Vehicle vehicle = vehicleRepository.findById(addInsuranceDto.getVehicleId()).orElseThrow();
        addInsurance.setVehicle(vehicle);
        addInsurance.setInsuranceCompany(addInsuranceDto.getInsuranceCompany());
        addInsurance.setPolicyNumber(addInsuranceDto.getPolicyNumber());
        addInsurance.setExpirationDate(LocalDate.parse(addInsuranceDto.getExpirationDate()));
        addInsurance.setCoverageType(addInsuranceDto.getCoverageType());
        addInsurance.setPremiumAmount(Double.parseDouble(addInsuranceDto.getPremiumAmount()));
        addInsurance.setStatus(addInsuranceDto.getStatus());
        insuranceRepository.save(addInsurance);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateInsuranceRequest updateInsuranceDto){
        Insurance updateInsurance = insuranceRepository.findById(id).orElseThrow();
        Vehicle vehicle = vehicleRepository.findById(updateInsuranceDto.getVehicleId()).orElseThrow();
        updateInsurance.setVehicle(vehicle);
        updateInsurance.setInsuranceCompany(updateInsuranceDto.getInsuranceCompany());
        updateInsurance.setPolicyNumber(updateInsuranceDto.getPolicyNumber());
        updateInsurance.setExpirationDate(LocalDate.parse(updateInsuranceDto.getExpirationDate()));
        updateInsurance.setCoverageType(updateInsuranceDto.getCoverageType());
        updateInsurance.setPremiumAmount(Double.parseDouble(updateInsuranceDto.getPremiumAmount()));
        updateInsurance.setStatus(updateInsuranceDto.getStatus());
        insuranceRepository.save(updateInsurance);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Insurance deleteInsurance = insuranceRepository.findById(id).orElseThrow();
        insuranceRepository.delete(deleteInsurance);
    }
}
