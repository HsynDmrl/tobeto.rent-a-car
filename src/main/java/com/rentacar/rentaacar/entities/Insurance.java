package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="insurances")
public class Insurance {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @Column(name="insurance_company")
    private String insuranceCompany;

    @Column(name ="policy_number")
    private String policyNumber;

    @Column(name="expiration_date", columnDefinition = "date")
    private LocalDate expirationDate;

    @Column(name="coverage_type")
    private String coverageType;

    @Column(name="premium_amount", columnDefinition = "double precision")
    private double premiumAmount;

    @Column(name="status")
    private String status;

    @OneToMany(mappedBy = "insurance")
    private List<Vehicle> vehicles;

}
