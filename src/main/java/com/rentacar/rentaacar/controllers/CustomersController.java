package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Customer.AddCustomerRequest;
import com.rentacar.rentaacar.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentacar.rentaacar.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.dtos.responses.Customer.GetCustomerResponse;
import com.rentacar.rentaacar.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.repositories.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomersController {
    private final CustomerRepository customerRepository;
    public CustomersController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping
    public List<GetCustomerListResponse> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<GetCustomerListResponse> getCustomerDtoList = new ArrayList<>();
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);

            GetCustomerListResponse dto = new GetCustomerListResponse();
            dto.setName(customer.getName());
            dto.setSurname(customer.getSurname());
            dto.setAddress(customer.getAddress());
            dto.setCity(customer.getCity());
            dto.setPhone(customer.getPhone());
            dto.setLicenseNumber(customer.getLicenseNumber());
            dto.setTcNo(customer.getTcNo());
            getCustomerDtoList.add(dto);
        }
        return getCustomerDtoList;
    }
    @GetMapping("{id}")
    public GetCustomerResponse getById(@PathVariable int id){
        Customer getByIdCustomerDto = customerRepository.findById(id).orElseThrow();
        GetCustomerResponse getByIdCustomer = new GetCustomerResponse();
        getByIdCustomer.setName(getByIdCustomerDto.getName());
        getByIdCustomer.setSurname(getByIdCustomerDto.getSurname());
        getByIdCustomer.setAddress(getByIdCustomerDto.getAddress());
        getByIdCustomer.setPhone(getByIdCustomerDto.getPhone());
        getByIdCustomer.setCity(getByIdCustomerDto.getCity());
        getByIdCustomer.setEmail(getByIdCustomerDto.getEmail());
        getByIdCustomer.setLicenseNumber(getByIdCustomerDto.getLicenseNumber());
        getByIdCustomer.setTcNo(getByIdCustomerDto.getTcNo());

        return getByIdCustomer;
    }
    @PostMapping
    public void add(@RequestBody AddCustomerRequest addCustomerDto){
        Customer addCustomer = new Customer();
        addCustomer.setName(addCustomerDto.getName());
        addCustomer.setSurname(addCustomerDto.getSurname());
        addCustomer.setAddress(addCustomerDto.getAddress());
        addCustomer.setCity(addCustomerDto.getCity());
        addCustomer.setPhone(addCustomerDto.getPhone());
        addCustomer.setEmail(addCustomerDto.getEmail());
        addCustomer.setLicenseNumber(addCustomerDto.getLicenseNumber());
        addCustomer.setTcNo(addCustomerDto.getTcNo());
        customerRepository.save(addCustomer);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateCustomerRequest updateCustomerDto){
        Customer updateCustomer = customerRepository.findById(id).orElseThrow();
        updateCustomer.setName(updateCustomerDto.getName());
        updateCustomer.setSurname(updateCustomerDto.getSurname());
        updateCustomer.setAddress(updateCustomerDto.getAddress());
        updateCustomer.setCity(updateCustomerDto.getCity());
        updateCustomer.setPhone(updateCustomerDto.getPhone());
        updateCustomer.setEmail(updateCustomerDto.getEmail());
        updateCustomer.setLicenseNumber(updateCustomerDto.getLicenseNumber());
        updateCustomer.setTcNo(updateCustomerDto.getTcNo());
        customerRepository.save(updateCustomer);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Customer deleteCustomer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(deleteCustomer);
    }
}
