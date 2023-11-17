package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @Column(name="id", unique = true)
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

    @Column(name="salary", columnDefinition = "double precision")
    private double salary;

    @Column(name="tc_no", unique = true)
    private String tcNo;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders;
}
