package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.abstracts.EmployeeService;
import com.rentacar.rentaacar.services.dtos.requests.Employee.AddEmployeeRequest;
import com.rentacar.rentaacar.services.dtos.requests.Employee.UpdateEmployeeRequest;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeesController {
    private final EmployeeService employeeService;
    @GetMapping
    public List<GetEmployeeListResponse> getAll() {
        return this.employeeService.getAll();
    }
    @GetMapping("{id}")
    public GetEmployeeResponse getById(@PathVariable int id){
        return this.employeeService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddEmployeeRequest addEmployeeDto){
        employeeService.add(addEmployeeDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateEmployeeRequest updateEmployeeDto){
        employeeService.update(id, updateEmployeeDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        employeeService.delete(id, areYouSure);
    }
    @GetMapping("/findByName")
    public List<GetEmployeeListResponse> findByName(String name)
    {
        return employeeService.findByName(name);
    }
    @GetMapping("/findByNameIsNot")
    public List<GetEmployeeListResponse> findByNameIsNot(String name)
    {
        return employeeService.findByNameIsNot(name);
    }
    @GetMapping("/getEmployeeFromCity")
    public List<GetEmployeeListResponse> getEmployeesFromCity(String city) {
        return employeeService.getEmployeesFromCity(city);
    }
    @GetMapping("getSurnameStartingWithLetter")
    public List<GetEmployeeListResponse> getEmployeesWithSurnameStartingWithLetter(String startLetter) {
        return employeeService.getEmployeesWithSurnameStartingWithLetter(startLetter);
    }
}
