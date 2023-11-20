package com.rentacar.rentaacar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="insurances")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="vehicle_id")
    @JsonIgnore
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
    @JsonIgnore
    private List<Vehicle> vehicles;

}
