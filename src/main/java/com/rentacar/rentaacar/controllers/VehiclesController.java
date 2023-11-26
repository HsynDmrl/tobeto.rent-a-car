package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.dtos.abstracts.VehicleService;
import com.rentacar.rentaacar.services.dtos.requests.Vehicle.AddVehicleRequest;
import com.rentacar.rentaacar.services.dtos.requests.Vehicle.UpdateVehicleRequest;
import com.rentacar.rentaacar.services.dtos.responses.Vehicle.GetVehicleListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Vehicle.GetVehicleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicles")
@AllArgsConstructor
public class VehiclesController {
    private final VehicleService vehicleService;
    @GetMapping
    public List<GetVehicleListResponse> getAll() {
        return this.vehicleService.getAll();
    }
    @GetMapping("{id}")
    public GetVehicleResponse getById(@PathVariable int id){
        return this.vehicleService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddVehicleRequest addVehicleDto){
        vehicleService.add(addVehicleDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateVehicleRequest updateVehicleDto){
        vehicleService.update(id, updateVehicleDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        vehicleService.delete(id, areYouSure);
    }
}
