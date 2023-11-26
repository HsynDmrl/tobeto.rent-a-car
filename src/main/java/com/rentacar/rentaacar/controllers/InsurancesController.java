package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.dtos.abstracts.InsuranceService;
import com.rentacar.rentaacar.services.dtos.abstracts.VehicleService;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.AddInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.UpdateInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceResponse;
import lombok.AllArgsConstructor;
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
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        insuranceService.delete(id, areYouSure);
    }
}
