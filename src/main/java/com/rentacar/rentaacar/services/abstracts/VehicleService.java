package com.rentacar.rentaacar.services.abstracts;

import com.rentacar.rentaacar.services.dtos.requests.Vehicle.AddVehicleRequest;
import com.rentacar.rentaacar.services.dtos.requests.Vehicle.UpdateVehicleRequest;
import com.rentacar.rentaacar.services.dtos.responses.Vehicle.GetVehicleListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Vehicle.GetVehicleResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VehicleService {
    public List<GetVehicleListResponse> getAll();
    public GetVehicleResponse getById(@PathVariable int id);
    public void add(@RequestBody AddVehicleRequest addVehicleDto);
    public void update(@PathVariable int id, @RequestBody UpdateVehicleRequest updateVehicleDto);
    public void delete(@PathVariable int id, String areYouSure);
}
