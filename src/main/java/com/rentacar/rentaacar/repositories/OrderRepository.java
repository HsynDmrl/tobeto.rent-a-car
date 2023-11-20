package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
