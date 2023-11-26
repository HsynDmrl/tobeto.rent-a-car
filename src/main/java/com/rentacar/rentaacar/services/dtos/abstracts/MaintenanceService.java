package com.rentacar.rentaacar.services.dtos.abstracts;

import com.rentacar.rentaacar.services.dtos.requests.Maintenance.AddMaintenanceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Maintenance.UpdateMaintenanceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MaintenanceService {
    public List<GetMaintenanceListResponse> getAll();
    public GetMaintenanceResponse getById(@PathVariable int id);
    public void add(@RequestBody AddMaintenanceRequest addMaintenanceDto);
    public void update(@PathVariable int id, @RequestBody UpdateMaintenanceRequest updateMaintenanceDto);
    public void delete(@PathVariable int id, String areYouSure);
}
