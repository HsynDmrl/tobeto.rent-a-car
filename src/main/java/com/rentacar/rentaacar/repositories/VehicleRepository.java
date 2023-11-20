package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
