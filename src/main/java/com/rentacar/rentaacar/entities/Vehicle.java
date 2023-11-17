package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    @Column(name="year")
    private int year;

    @Column(name="color")
    private String color;

    @Column(name="daily_cost")
    private double dailyCost;

    @Column(name="status")
    private String status;

    @Column(name="plate_number", unique = true)
    private String plateNumber;

    @Column(name="odometer")
    private int odometer;

    @Column(name="fuel_type")
    private String fuelType;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    @ManyToOne
    @JoinColumn(name="insurance_id")
    private Insurance insurance;

    @ManyToOne
    @JoinColumn(name="maintenance_id")
    private Maintenance maintenance;

    @OneToMany(mappedBy = "vehicle")
    private List<Maintenance> maintenances;
    @OneToMany(mappedBy = "vehicle")
    private List<Insurance> insurances;
}
