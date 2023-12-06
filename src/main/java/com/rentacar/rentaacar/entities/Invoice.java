package com.rentacar.rentaacar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    @JoinColumn(name="customer_id")
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

    @OneToMany(mappedBy = "invoice")
    @JsonIgnore
    private List<Order> orders;

    @Transient
    private String deleteConfirmation;

    public boolean isDeleteConfirmed() {
        return "evet".equalsIgnoreCase(deleteConfirmation);
    }
}
