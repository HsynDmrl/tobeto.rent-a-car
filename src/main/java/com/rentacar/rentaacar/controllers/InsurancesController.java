package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.repositories.InsuranceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/insurances")
public class InsurancesController {
    private final InsuranceRepository insuranceRepository;
    public InsurancesController(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }
    @GetMapping
    public List<Insurance> getAll(){
        return insuranceRepository.findAll();
    }
    @GetMapping("{id}")
    public Insurance getById(@PathVariable int id){
        return insuranceRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Insurance insurance){
        insuranceRepository.save(insurance);
    }
    @PutMapping
    public void update(@RequestBody Insurance insurance){
        insuranceRepository.findById(insurance.getId()).orElseThrow();
        insuranceRepository.save(insurance);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Insurance insuranceToDelete = insuranceRepository.findById(id).orElseThrow();
        insuranceRepository.delete(insuranceToDelete);
    }
}
