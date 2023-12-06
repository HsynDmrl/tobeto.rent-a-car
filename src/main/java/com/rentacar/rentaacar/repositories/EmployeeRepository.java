package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Derived sorgu 1: İsmi verilen çalışanları getir
    List<Employee> findByName(String name);

    // Derived sorgu 2: İsmi verilen çalışanları hariç tutarak getir
    List<Employee> findByNameIsNot(String name);

    // JPQL sorgu 1: Şehre göre çalışanları getir
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse(e.name, e.surname, e.title, e.email, e.phone, e.hireDate, e.address, e.city, e.salary, e.tcNo) FROM Employee e WHERE e.city LIKE %:city%")
    List<GetEmployeeListResponse> getEmployeesFromCity(String city);

    // JPQL sorgu 2: Belirli bir harfle başlayan soyadına sahip çalışanları getir
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse(e.name, e.surname, e.title, e.email, e.phone, e.hireDate, e.address, e.city, e.salary, e.tcNo) FROM Employee e WHERE UPPER(SUBSTRING(e.surname, 1, 1)) = UPPER(:startLetter)")
    List<GetEmployeeListResponse> getEmployeesWithSurnameStartingWithLetter(String startLetter);

}
