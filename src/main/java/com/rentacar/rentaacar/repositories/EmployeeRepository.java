package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
