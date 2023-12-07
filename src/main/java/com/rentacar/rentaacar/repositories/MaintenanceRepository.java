package com.rentacar.rentaacar.repositories;

import com.rentacar.rentaacar.entities.Maintenance;
import com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    boolean existsByNextMaintenanceDateBeforeAndMaintenanceDateAfter(LocalDate nextMaintenanceDate, LocalDate maintenanceDate);


    // Derived Query: Maintenance tipine göre bakımları getir
    List<Maintenance> findByMaintenanceType(String maintenanceType);

    // Derived Query: Belirli bir mekanik tarafından yapılan bakımları getir
    List<Maintenance> findByMechanic(String mechanic);

    // JPQL Sorgusu: Gelecekteki bakım tarihleri
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceListResponse(m.maintenanceDate, m.maintenanceType, m.cost, m.mechanic, m.nextMaintenanceDate, m.description) FROM Maintenance m WHERE m.maintenanceDate >= CURRENT_DATE")
    List<GetMaintenanceListResponse> getUpcomingMaintenances();

    // JPQL Sorgusu: En yüksek maliyetli bakım
    @Query("SELECT new com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceResponse(m.maintenanceDate, m.maintenanceType, m.cost, m.mechanic, m.nextMaintenanceDate, m.description) FROM Maintenance m WHERE m.cost = (SELECT MAX(m.cost) FROM Maintenance m)")
    List<GetMaintenanceListResponse> getMaxCostMaintenance();
}
