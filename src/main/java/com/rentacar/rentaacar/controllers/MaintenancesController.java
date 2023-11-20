package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.entities.Maintenance;
import com.rentacar.rentaacar.repositories.MaintenanceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maintenances")
public class MaintenancesController {
    private final MaintenanceRepository maintenanceRepository;
    public MaintenancesController(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
    @GetMapping
    public List<Maintenance> getAll(){
        return maintenanceRepository.findAll();
    }
    @GetMapping("{id}")
    public Maintenance getById(@PathVariable int id){
        return maintenanceRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Maintenance maintenance){
        maintenanceRepository.save(maintenance);
    }
    @PutMapping
    public void update(@RequestBody Maintenance maintenance){
        maintenanceRepository.findById(maintenance.getId()).orElseThrow();
        maintenanceRepository.save(maintenance);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Maintenance maintenanceToDelete = maintenanceRepository.findById(id).orElseThrow();
        maintenanceRepository.delete(maintenanceToDelete);
    }
}