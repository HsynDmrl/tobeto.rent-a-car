package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Maintenance.AddMaintenanceRequest;
import com.rentacar.rentaacar.dtos.requests.Maintenance.UpdateMaintenanceRequest;
import com.rentacar.rentaacar.dtos.responses.Maintenance.GetMaintenanceListResponse;
import com.rentacar.rentaacar.dtos.responses.Maintenance.GetMaintenanceResponse;
import com.rentacar.rentaacar.dtos.responses.Maintenance.GetMaintenanceListResponse;
import com.rentacar.rentaacar.entities.Maintenance;
import com.rentacar.rentaacar.repositories.*;
import org.springframework.web.bind.annotation.*;
import com.rentacar.rentaacar.entities.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/maintenances")
public class MaintenancesController {
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    public MaintenancesController(MaintenanceRepository maintenanceRepository, VehicleRepository vehicleRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;
    }
    @GetMapping
    public List<GetMaintenanceListResponse> getAll() {
        List<Maintenance> maintenanceList = maintenanceRepository.findAll();
        List<GetMaintenanceListResponse> getMaintenanceDtoList = new ArrayList<>();
        for (int i = 0; i < maintenanceList.size(); i++) {
            Maintenance maintenance = maintenanceList.get(i);
            GetMaintenanceListResponse dto = new GetMaintenanceListResponse();
            dto.setMaintenanceDate(maintenance.getMaintenanceDate());
            dto.setMaintenanceType(maintenance.getMaintenanceType());
            dto.setCost(maintenance.getCost());
            dto.setMechanic(maintenance.getMechanic());
            dto.setNextMaintenanceDate(maintenance.getNextMaintenanceDate());
            dto.setDescription(maintenance.getDescription());
            getMaintenanceDtoList.add(dto);
        }
        return getMaintenanceDtoList;
    }
    @GetMapping("{id}")
    public GetMaintenanceResponse getById(@PathVariable int id){
        Maintenance getByIdMaintenanceDto = maintenanceRepository.findById(id).orElseThrow();
        GetMaintenanceResponse getByIdMaintenance = new GetMaintenanceResponse();
        getByIdMaintenance.setMaintenanceDate(getByIdMaintenanceDto.getMaintenanceDate());
        getByIdMaintenance.setMaintenanceType(getByIdMaintenanceDto.getMaintenanceType());
        getByIdMaintenance.setCost(getByIdMaintenanceDto.getCost());
        getByIdMaintenance.setMechanic(getByIdMaintenanceDto.getMechanic());
        getByIdMaintenance.setNextMaintenanceDate(getByIdMaintenanceDto.getNextMaintenanceDate());
        getByIdMaintenance.setDescription(getByIdMaintenanceDto.getDescription());

        return getByIdMaintenance;
    }
    @PostMapping
    public void add(@RequestBody AddMaintenanceRequest addMaintenanceDto){
        Maintenance addMaintenance = new Maintenance();
        Vehicle vehicle = vehicleRepository.findById(addMaintenanceDto.getVehicleId()).orElseThrow();
        addMaintenance.setVehicle(vehicle);
        addMaintenance.setMaintenanceDate(addMaintenanceDto.getMaintenanceDate());
        addMaintenance.setMaintenanceType(addMaintenanceDto.getMaintenanceType());
        addMaintenance.setCost(addMaintenanceDto.getCost());
        addMaintenance.setMechanic(addMaintenanceDto.getMechanic());
        addMaintenance.setNextMaintenanceDate(addMaintenanceDto.getNextMaintenanceDate());
        addMaintenance.setDescription(addMaintenanceDto.getDescription());
        maintenanceRepository.save(addMaintenance);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest updateMaintenanceDto){
        Maintenance updateMaintenance = maintenanceRepository.findById(id).orElseThrow();
        Vehicle vehicle = vehicleRepository.findById(updateMaintenanceDto.getVehicleId()).orElseThrow();
        updateMaintenance.setVehicle(vehicle);
        updateMaintenance.setMaintenanceDate(updateMaintenanceDto.getMaintenanceDate());
        updateMaintenance.setMaintenanceType(updateMaintenanceDto.getMaintenanceType());
        updateMaintenance.setCost(updateMaintenanceDto.getCost());
        updateMaintenance.setMechanic(updateMaintenanceDto.getMechanic());
        updateMaintenance.setNextMaintenanceDate(updateMaintenanceDto.getNextMaintenanceDate());
        updateMaintenance.setDescription(updateMaintenanceDto.getDescription());
        maintenanceRepository.save(updateMaintenance);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Maintenance deleteMaintenance = maintenanceRepository.findById(id).orElseThrow();
        maintenanceRepository.delete(deleteMaintenance);
    }
}
