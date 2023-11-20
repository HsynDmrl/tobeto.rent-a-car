package com.rentacar.rentaacar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="maintenances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    @JsonIgnore
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
    @JsonIgnore
    private List<Vehicle> vehicles;
}
