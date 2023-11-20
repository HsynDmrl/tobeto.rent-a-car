package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.entities.Vehicle;
import com.rentacar.rentaacar.repositories.VehicleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicles")
public class VehiclesController {
    private final VehicleRepository vehicleRepository;
    public VehiclesController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    @GetMapping
    public List<Vehicle> getAll(){
        return vehicleRepository.findAll();
    }
    @GetMapping("{id}")
    public Vehicle getById(@PathVariable int id){
        return vehicleRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Vehicle vehicle){
        vehicleRepository.save(vehicle);
    }
    @PutMapping
    public void update(@RequestBody Vehicle vehicle){
        vehicleRepository.findById(vehicle.getId()).orElseThrow();
        vehicleRepository.save(vehicle);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Vehicle vehicleToDelete = vehicleRepository.findById(id).orElseThrow();
        vehicleRepository.delete(vehicleToDelete);
    }
}
