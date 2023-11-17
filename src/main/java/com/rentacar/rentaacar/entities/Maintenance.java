package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="maintenances")
public class Maintenance {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @Column(name="maintenance_date", columnDefinition = "date")
    private LocalDate maintenanceDate;

    @Column(name="maintenance_type")
    private String maintenanceType;

    @Column(name="cost", columnDefinition = "double precision")
    private Double cost;

    @Column(name="mechanic")
    private String mechanic;

    @Column(name="next_maintenance_date", columnDefinition = "date")
    private LocalDate nextMaintenanceDate;

    @Column(name="description", columnDefinition = "text")
    private String description;

    @OneToMany(mappedBy = "maintenance")
    private List<Vehicle> vehicles;
}
