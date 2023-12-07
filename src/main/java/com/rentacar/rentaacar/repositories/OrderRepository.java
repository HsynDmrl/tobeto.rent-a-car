package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    boolean existsByPickUpDateAfterAndDropDateBefore(LocalDate dropDate, LocalDate pickUpDate);

}
