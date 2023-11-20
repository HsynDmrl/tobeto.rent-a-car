package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
