package com.rentacar.rentaacar.services.abstracts;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentacar.rentaacar.services.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    public List<GetCustomerListResponse> getAll();
    public GetCustomerResponse getById(@PathVariable int id);
    public void add(AddCustomerRequest addCustomerDto);
    public void update(@PathVariable int id, @RequestBody UpdateCustomerRequest updateCustomerDto);
    public void delete(@PathVariable int id, String areYouSure);

    List<GetCustomerListResponse> findByName(String name);
    List<GetCustomerListResponse> findByNameIsNot(String name);
    List<GetCustomerListResponse> getCustomerFromCity(String city);
    List<GetCustomerListResponse> getCustomersWithSurnameStartingWithLetter(String startLetter);
}
