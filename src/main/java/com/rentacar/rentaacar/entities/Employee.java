package com.rentacar.rentaacar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="title")
    private String title;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="hire_date",  columnDefinition = "date")
    private LocalDate hireDate;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="salary", columnDefinition = "double precision")
    private double salary;

    @Column(name="tc_no", unique = true)
    private String tcNo;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Order> orders;

    @Transient
    private String deleteConfirmation;

    public boolean isDeleteConfirmed() {
        return "evet".equalsIgnoreCase(deleteConfirmation);
    }
}
