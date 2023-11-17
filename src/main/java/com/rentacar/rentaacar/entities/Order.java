package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="costumer_id")
    private Costumer costumer;

    @Column(name="pick_up_date", columnDefinition = "date")
    private LocalDate pickUpDate;

    @Column(name="drop_date", columnDefinition = "date")
    private LocalDate dropDate;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @Column(name="delivered")
    private boolean delivered;

    @Column(name="notes", columnDefinition = "text")
    private String notes;

    @Column(name="total_cost", columnDefinition = "double precision")
    private double totalCost;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;
    @OneToMany(mappedBy = "order")
    private List<Invoice> invoices;
}
