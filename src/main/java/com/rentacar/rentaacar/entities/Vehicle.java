package com.rentacar.rentaacar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @Column(name="id")
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
    @JsonIgnore
    private Insurance insurance;

    @ManyToOne
    @JoinColumn(name="maintenance_id")
    @JsonIgnore
    private Maintenance maintenance;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "vehicle")
    @JsonIgnore
    private List<Insurance> insurances;
}
