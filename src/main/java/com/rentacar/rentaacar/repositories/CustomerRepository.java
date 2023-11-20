package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
