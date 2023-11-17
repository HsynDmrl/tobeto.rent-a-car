package com.rentacar.rentaacar.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="costumers")
public class Costumer {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="address")
    private String address;

    @Column(name="city")
    private String city;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="license_number")
    private String licenseNumber;

    @Column(name="tc_no", unique = true)
    private String tcNo;

    @OneToMany(mappedBy = "costumer")
    private List<Order> orders;
    @OneToMany(mappedBy = "costumer")
    private List<Invoice> invoices;
}
