package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.abstracts.CustomerService;
import com.rentacar.rentaacar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentacar.rentaacar.services.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customers")
@AllArgsConstructor
public class CustomersController {
    private final CustomerService customerService;
    @GetMapping
    public List<GetCustomerListResponse> getAll() {
        return this.customerService.getAll();
    }
    @GetMapping("{id}")
    public GetCustomerResponse getById(@PathVariable int id){
        return this.customerService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddCustomerRequest addCustomerDto){
        customerService.add(addCustomerDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateCustomerRequest updateCustomerDto){
        customerService.update(id, updateCustomerDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        customerService.delete(id, areYouSure);
    }

    @GetMapping("/findByName")
    public List<GetCustomerListResponse> findByName(String name)
    {
        return customerService.findByName(name);
    }
    @GetMapping("/findByNameIsNot")
    public List<GetCustomerListResponse> findByNameIsNot(String name)
    {
        return customerService.findByNameIsNot(name);
    }
    @GetMapping("/getCustomerFromCity")
    public List<GetCustomerListResponse> getCustomerFromCity(String city) {
        return customerService.getCustomerFromCity(city);
    }
    @GetMapping("getSurnameStartingWithLetter")
    public List<GetCustomerListResponse> getCustomersWithSurnameStartingWithLetter(String startLetter) {
        return customerService.getCustomersWithSurnameStartingWithLetter(startLetter);
    }
}
