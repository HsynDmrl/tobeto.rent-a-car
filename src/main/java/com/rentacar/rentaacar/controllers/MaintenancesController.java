package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.abstracts.MaintenanceService;
import com.rentacar.rentaacar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Maintenance.AddMaintenanceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Maintenance.UpdateMaintenanceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/maintenances")
@AllArgsConstructor
public class MaintenancesController {
    private final MaintenanceService maintenanceService;
    @GetMapping
    public List<GetMaintenanceListResponse> getAll() {
        return this.maintenanceService.getAll();
    }
    @GetMapping("{id}")
    public GetMaintenanceResponse getById(@PathVariable int id){
        return this.maintenanceService.getById(id);
    }
    @PostMapping("/add")
    public void add(@RequestBody @Valid AddMaintenanceRequest addMaintenanceDto){
        this.maintenanceService.add(addMaintenanceDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest updateMaintenanceDto){
        maintenanceService.update(id, updateMaintenanceDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        maintenanceService.delete(id, areYouSure);
    }

    @GetMapping("/findByMaintenanceType")
    List<GetMaintenanceListResponse> findByMaintenanceType(String maintenanceType)
    {
        return maintenanceService.findByMaintenanceType(maintenanceType);
    }
    @GetMapping("/findByMechanic")
    List<GetMaintenanceListResponse> findByMechanic(String mechanic)
    {
        return maintenanceService.findByMechanic(mechanic);
    }
    @GetMapping("/getUpcomingMaintenances")
    List<GetMaintenanceListResponse> getUpcomingMaintenances()
    {
        return maintenanceService.getUpcomingMaintenances();
    }
    @GetMapping("getMaxCostMaintenance")
    public List<GetMaintenanceListResponse> getMaxCostMaintenance() {
        return maintenanceService.getMaxCostMaintenance();
    }
}
