package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Payment;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    boolean existsByTransactionNo(String transactionNo);

    // 2x Derived (Örnek)
    List<Payment> findByAmount(double amount);
    List<Payment> findByCurrency(String currency);

    // 2x JPQL (Örnek)
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentListResponse(p.paymentDate, p.paymentMethod, p.amount, p.currency, p.transactionNo, p.status) FROM Payment p WHERE p.status = :status")
    List<GetPaymentListResponse> getPaymentsByStatus(int status);

    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentResponse(p.paymentDate, p.paymentMethod, p.amount, p.currency, p.transactionNo, p.status) FROM Payment p WHERE p.transactionNo = :transactionNo")
    List<GetPaymentListResponse> getPaymentByTransactionNo(String transactionNo);

}
