package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Vehicle.AddVehicleRequest;
import com.rentacar.rentaacar.dtos.requests.Vehicle.UpdateVehicleRequest;
import com.rentacar.rentaacar.dtos.responses.Vehicle.GetVehicleListResponse;
import com.rentacar.rentaacar.dtos.responses.Vehicle.GetVehicleListResponse;
import com.rentacar.rentaacar.dtos.responses.Vehicle.GetVehicleResponse;
import com.rentacar.rentaacar.entities.*;
import com.rentacar.rentaacar.repositories.CustomerRepository;
import com.rentacar.rentaacar.repositories.InsuranceRepository;
import com.rentacar.rentaacar.repositories.MaintenanceRepository;
import com.rentacar.rentaacar.repositories.VehicleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/vehicles")
public class VehiclesController {
    private final VehicleRepository vehicleRepository;

    private final InsuranceRepository insuranceRepository;
    private final MaintenanceRepository maintenanceRepository;
    public VehiclesController(VehicleRepository vehicleRepository, InsuranceRepository insuranceRepository, MaintenanceRepository maintenanceRepository) {
        this.vehicleRepository = vehicleRepository;
        this.insuranceRepository = insuranceRepository;
        this.maintenanceRepository = maintenanceRepository;
    }
    @GetMapping
    public List<GetVehicleListResponse> getAll() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<GetVehicleListResponse> getVehicleDtoList = new ArrayList<>();
        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle vehicle = vehicleList.get(i);

            GetVehicleListResponse dto = new GetVehicleListResponse();
            dto.setBrand(vehicle.getBrand());
            dto.setModel(vehicle.getModel());
            dto.setYear(vehicle.getYear());
            dto.setColor(vehicle.getColor());
            dto.setDailyCost(vehicle.getDailyCost());
            dto.setStatus(vehicle.getStatus());
            dto.setPlateNumber(vehicle.getPlateNumber());
            dto.setOdometer(vehicle.getOdometer());
            dto.setFuelType(vehicle.getFuelType());
            dto.setNotes(vehicle.getNotes());
            getVehicleDtoList.add(dto);
        }
        return getVehicleDtoList;
    }
    @GetMapping("{id}")
    public GetVehicleResponse getById(@PathVariable int id){
        Vehicle getByIdVehicleDto = vehicleRepository.findById(id).orElseThrow();
        GetVehicleResponse getByIdVehicle = new GetVehicleResponse();
        getByIdVehicle.setBrand(getByIdVehicleDto.getBrand());
        getByIdVehicle.setModel(getByIdVehicleDto.getModel());
        getByIdVehicle.setYear(getByIdVehicleDto.getYear());
        getByIdVehicle.setColor(getByIdVehicleDto.getColor());
        getByIdVehicle.setDailyCost(getByIdVehicleDto.getDailyCost());
        getByIdVehicle.setStatus(getByIdVehicleDto.getStatus());
        getByIdVehicle.setPlateNumber(getByIdVehicleDto.getPlateNumber());
        getByIdVehicle.setOdometer(getByIdVehicleDto.getOdometer());
        getByIdVehicle.setFuelType(getByIdVehicleDto.getFuelType());
        getByIdVehicle.setNotes(getByIdVehicleDto.getNotes());

        return getByIdVehicle;
    }
    @PostMapping
    public void add(@RequestBody AddVehicleRequest addVehicleDto){
        Vehicle addVehicle = new Vehicle();
        addVehicle.setBrand(addVehicleDto.getBrand());
        addVehicle.setModel(addVehicleDto.getModel());
        addVehicle.setYear(addVehicleDto.getYear());
        addVehicle.setColor(addVehicleDto.getColor());
        addVehicle.setDailyCost(addVehicleDto.getDailyCost());
        addVehicle.setStatus(addVehicleDto.getStatus());
        addVehicle.setPlateNumber(addVehicleDto.getPlateNumber());
        addVehicle.setOdometer(addVehicleDto.getOdometer());
        addVehicle.setFuelType(addVehicleDto.getFuelType());
        addVehicle.setNotes(addVehicleDto.getNotes());

        Insurance insurance = insuranceRepository.findById(addVehicleDto.getInsoranceId()).orElseThrow();
        addVehicle.setInsurance(insurance);
        Maintenance maintenance = maintenanceRepository.findById(addVehicleDto.getMaintenanceId()).orElseThrow();
        addVehicle.setMaintenance(maintenance);
        vehicleRepository.save(addVehicle);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateVehicleRequest updateVehicleDto){
        Vehicle updateVehicle = vehicleRepository.findById(id).orElseThrow();
        updateVehicle.setBrand(updateVehicleDto.getBrand());
        updateVehicle.setModel(updateVehicleDto.getModel());
        updateVehicle.setYear(updateVehicleDto.getYear());
        updateVehicle.setColor(updateVehicleDto.getColor());
        updateVehicle.setDailyCost(updateVehicleDto.getDailyCost());
        updateVehicle.setStatus(updateVehicleDto.getStatus());
        updateVehicle.setPlateNumber(updateVehicleDto.getPlateNumber());
        updateVehicle.setOdometer(updateVehicleDto.getOdometer());
        updateVehicle.setFuelType(updateVehicleDto.getFuelType());
        updateVehicle.setNotes(updateVehicleDto.getNotes());
        Insurance insurance = insuranceRepository.findById(updateVehicleDto.getInsoranceId()).orElseThrow();
        updateVehicle.setInsurance(insurance);
        Maintenance maintenance = maintenanceRepository.findById(updateVehicleDto.getMaintenanceId()).orElseThrow();
        updateVehicle.setMaintenance(maintenance);
        vehicleRepository.save(updateVehicle);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Vehicle deleteVehicle = vehicleRepository.findById(id).orElseThrow();
        vehicleRepository.delete(deleteVehicle);
    }
}
