package com.rentacar.rentaacar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;

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
