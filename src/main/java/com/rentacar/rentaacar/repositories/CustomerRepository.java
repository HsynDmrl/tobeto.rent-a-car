package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByEmail(String email);
    boolean existsByTcNo(String tcNo);

    // 2x Derived
    List<Customer> findByName(String name);
    List<Customer> findByNameIsNot(String name);
    // 2x JPQL

    boolean existsByName(String name);

    @Query(value = "SELECT new com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse(c.name, c.surname, c.address, c.city, c.phone, c.email, c.licenseNumber, c.tcNo) from Customer c where c.city like %:city% ")
    List<GetCustomerListResponse> getCustomerFromCity(String city);

    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse(c.name, c.surname, c.address, c.city, c.phone, c.email, c.licenseNumber, c.tcNo) FROM Customer c WHERE UPPER(SUBSTRING(c.surname, 1, 1)) = UPPER(:startLetter)")
    List<GetCustomerListResponse> getCustomersWithSurnameStartingWithLetter(String startLetter);

}
