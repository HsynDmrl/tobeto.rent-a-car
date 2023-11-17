package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "payment_date", columnDefinition = "date")
    private LocalDate paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "amount", columnDefinition = "double precision")
    private double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transaction_no")
    private String transactionNo;

    @Column(name = "status")
    private int status;
}
