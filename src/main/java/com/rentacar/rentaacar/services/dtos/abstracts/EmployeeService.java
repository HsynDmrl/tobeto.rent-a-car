package com.rentacar.rentaacar.services.dtos.abstracts;

import com.rentacar.rentaacar.services.dtos.requests.Employee.AddEmployeeRequest;
import com.rentacar.rentaacar.services.dtos.requests.Employee.UpdateEmployeeRequest;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {
    public List<GetEmployeeListResponse> getAll();
    public GetEmployeeResponse getById(@PathVariable int id);
    public void add(@RequestBody AddEmployeeRequest addEmployeeDto);
    public void update(@PathVariable int id, @RequestBody UpdateEmployeeRequest updateEmployeeDto);
    public void delete(@PathVariable int id, String areYouSure);
}
