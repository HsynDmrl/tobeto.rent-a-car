package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="invoices")
public class Invoice {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="costumer_id")
    private Costumer costumer;

    @Column(name="invoice_date", columnDefinition = "date")
    private LocalDate invoiceDate;

    @Column(name="due_date", columnDefinition = "date")
    private LocalDate dueDate;

    @Column(name="total_amount", columnDefinition = "double precision")
    private double totalAmount;

    @Column(name="currency")
    private String currency;

    @Column(name="status")
    private String status;

}
