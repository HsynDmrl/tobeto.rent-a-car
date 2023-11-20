package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
