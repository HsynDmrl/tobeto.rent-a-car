package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Employee.AddEmployeeRequest;
import com.rentacar.rentaacar.dtos.requests.Employee.UpdateEmployeeRequest;
import com.rentacar.rentaacar.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentacar.rentaacar.dtos.responses.Employee.GetEmployeeResponse;
import com.rentacar.rentaacar.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeesController {
    private final EmployeeRepository employeeRepository;
    public EmployeesController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @GetMapping
    public List<GetEmployeeListResponse> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<GetEmployeeListResponse> getEmployeeDtoList = new ArrayList<>();
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);

            GetEmployeeListResponse dto = new GetEmployeeListResponse();
            dto.setName(employee.getName());
            dto.setSurname(employee.getSurname());
            dto.setTitle(employee.getTitle());
            dto.setEmail(employee.getEmail());
            dto.setPhone(employee.getPhone());
            dto.setHireDate(employee.getHireDate());
            dto.setAddress(employee.getAddress());
            dto.setCity(employee.getCity());
            dto.setSalary(employee.getSalary());
            dto.setTcNo(employee.getTcNo());
            getEmployeeDtoList.add(dto);
        }
        return getEmployeeDtoList;
    }
    @GetMapping("{id}")
    public GetEmployeeResponse getById(@PathVariable int id){
        Employee getByIdEmployeeDto = employeeRepository.findById(id).orElseThrow();
        GetEmployeeResponse getByIdEmployee = new GetEmployeeResponse();
        getByIdEmployee.setName(getByIdEmployeeDto.getName());
        getByIdEmployee.setSurname(getByIdEmployeeDto.getSurname());
        getByIdEmployee.setTitle(getByIdEmployeeDto.getTitle());
        getByIdEmployee.setEmail(getByIdEmployeeDto.getEmail());
        getByIdEmployee.setPhone(getByIdEmployeeDto.getPhone());
        getByIdEmployee.setHireDate(getByIdEmployeeDto.getHireDate());
        getByIdEmployee.setAddress(getByIdEmployeeDto.getAddress());
        getByIdEmployee.setCity(getByIdEmployeeDto.getCity());
        getByIdEmployee.setSalary(getByIdEmployeeDto.getSalary());
        getByIdEmployee.setTcNo(getByIdEmployeeDto.getTcNo());

        return getByIdEmployee;
    }
    @PostMapping
    public void add(@RequestBody AddEmployeeRequest addEmployeeDto){
        Employee addEmployee = new Employee();
        addEmployee.setName(addEmployeeDto.getName());
        addEmployee.setSurname(addEmployeeDto.getSurname());
        addEmployee.setTitle(addEmployeeDto.getTitle());
        addEmployee.setEmail(addEmployeeDto.getEmail());
        addEmployee.setPhone(addEmployeeDto.getPhone());
        addEmployee.setHireDate(addEmployeeDto.getHireDate());
        addEmployee.setAddress(addEmployeeDto.getAddress());
        addEmployee.setCity(addEmployeeDto.getCity());
        addEmployee.setSalary(addEmployeeDto.getSalary());
        addEmployee.setTcNo(addEmployeeDto.getTcNo());
        employeeRepository.save(addEmployee);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateEmployeeRequest updateEmployeeDto){
        Employee updateEmployee = employeeRepository.findById(id).orElseThrow();
        updateEmployee.setName(updateEmployeeDto.getName());
        updateEmployee.setSurname(updateEmployeeDto.getSurname());
        updateEmployee.setTitle(updateEmployeeDto.getTitle());
        updateEmployee.setEmail(updateEmployeeDto.getEmail());
        updateEmployee.setPhone(updateEmployeeDto.getPhone());
        updateEmployee.setHireDate(updateEmployeeDto.getHireDate());
        updateEmployee.setAddress(updateEmployeeDto.getAddress());
        updateEmployee.setCity(updateEmployeeDto.getCity());
        updateEmployee.setSalary(updateEmployeeDto.getSalary());
        updateEmployee.setTcNo(updateEmployeeDto.getTcNo());
        employeeRepository.save(updateEmployee);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Employee deleteEmployee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(deleteEmployee);
    }
}
