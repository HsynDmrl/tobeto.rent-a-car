package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
}
