package com.rentacar.rentaacar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;

    @Column(name="pick_up_date", columnDefinition = "date")
    private LocalDate pickUpDate;

    @Column(name="drop_date", columnDefinition = "date")
    private LocalDate dropDate;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    @JsonIgnore
    private Vehicle vehicle;

    @Column(name="delivered")
    private boolean delivered;

    @Column(name="notes", columnDefinition = "text")
    private String notes;

    @Column(name="total_cost", columnDefinition = "double precision")
    private double totalCost;

    @ManyToOne
    @JoinColumn(name="employee_id")
    @JsonIgnore
    private Employee employee;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<Payment> payments;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<Invoice> invoices;
}
