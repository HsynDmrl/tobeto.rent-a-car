package com.rentacar.rentaacar.services.dtos.concretes;

import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.entities.Maintenance;
import com.rentacar.rentaacar.entities.Payment;
import com.rentacar.rentaacar.entities.Vehicle;
import com.rentacar.rentaacar.repositories.MaintenanceRepository;
import com.rentacar.rentaacar.repositories.VehicleRepository;
import com.rentacar.rentaacar.services.dtos.abstracts.MaintenanceService;
import com.rentacar.rentaacar.services.dtos.requests.Maintenance.AddMaintenanceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Maintenance.UpdateMaintenanceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Maintenance.GetMaintenanceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;
    @Override
    public List<GetMaintenanceListResponse> getAll() {
        List<Maintenance> maintenanceList = maintenanceRepository.findAll();
        List<GetMaintenanceListResponse> getMaintenanceDtoList = new ArrayList<>();

        if (maintenanceList.isEmpty())
            throw new RuntimeException("Bakım kaydı bulunmuyor.");

        for (Maintenance maintenance : maintenanceList) {
            GetMaintenanceListResponse dto = new GetMaintenanceListResponse();
            dto.setMaintenanceDate(maintenance.getMaintenanceDate());
            dto.setMaintenanceType(maintenance.getMaintenanceType());
            dto.setCost(maintenance.getCost());
            dto.setMechanic(maintenance.getMechanic());
            dto.setNextMaintenanceDate(maintenance.getNextMaintenanceDate());
            dto.setDescription(maintenance.getDescription());
            getMaintenanceDtoList.add(dto);
        }
        return getMaintenanceDtoList;
    }
    @Override
    public GetMaintenanceResponse getById(int id) {
        Maintenance getByIdMaintenanceDto = maintenanceRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bu ID ile bir bakım kaydı bulunamadı."));

        GetMaintenanceResponse getByIdMaintenance = new GetMaintenanceResponse();
        getByIdMaintenance.setMaintenanceDate(getByIdMaintenanceDto.getMaintenanceDate());
        getByIdMaintenance.setMaintenanceType(getByIdMaintenanceDto.getMaintenanceType());
        getByIdMaintenance.setCost(getByIdMaintenanceDto.getCost());
        getByIdMaintenance.setMechanic(getByIdMaintenanceDto.getMechanic());
        getByIdMaintenance.setNextMaintenanceDate(getByIdMaintenanceDto.getNextMaintenanceDate());
        getByIdMaintenance.setDescription(getByIdMaintenanceDto.getDescription());
        return getByIdMaintenance;
    }
    @Override
    public void add(AddMaintenanceRequest addMaintenanceDto) {
        Vehicle vehicle = vehicleRepository.findById(addMaintenanceDto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir Araç bulunamadı."));

        if (addMaintenanceDto.getMaintenanceDate() == null)
            throw new RuntimeException("Bakım tarihi boş olamaz.");

        if (addMaintenanceDto.getMaintenanceType().length() < 2)
            throw new RuntimeException("Bakım türü 2 karakterden az olamaz.");

        if (addMaintenanceDto.getCost() <= 0)
            throw new RuntimeException("Bakımı yapan mekanik veya servis sağlayıcısı boş olamaz.");

        if (addMaintenanceDto.getMechanic().isEmpty())
            throw new RuntimeException("Mekanik bilgisi boş olamaz.");

        if (addMaintenanceDto.getNextMaintenanceDate() == null)
            throw new RuntimeException("Sonraki bakım tarihi boş olamaz.");

        Maintenance addMaintenance = new Maintenance();
        addMaintenance.setVehicle(vehicle);
        addMaintenance.setMaintenanceDate(addMaintenanceDto.getMaintenanceDate());
        addMaintenance.setMaintenanceType(addMaintenanceDto.getMaintenanceType());
        addMaintenance.setCost(addMaintenanceDto.getCost());
        addMaintenance.setMechanic(addMaintenanceDto.getMechanic());
        addMaintenance.setNextMaintenanceDate(addMaintenanceDto.getNextMaintenanceDate());
        addMaintenance.setDescription(addMaintenanceDto.getDescription());
        maintenanceRepository.save(addMaintenance);
    }
    @Override
    public void update(int id, UpdateMaintenanceRequest updateMaintenanceDto) {
        Maintenance updateMaintenance = maintenanceRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Bu ID ile bir bakım kaydı bulunamadı."));

        Vehicle vehicle = vehicleRepository.findById(updateMaintenanceDto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir Araç bulunamadı."));

        if (updateMaintenanceDto.getMaintenanceDate() == null)
            throw new RuntimeException("Güncellenen Bakım tarihi boş olamaz.");

        if (updateMaintenanceDto.getMaintenanceType().length() < 2)
            throw new RuntimeException("Güncellenen Bakım 2 karakterden az olamaz.");

        if (updateMaintenanceDto.getCost() <= 0)
            throw new RuntimeException("Güncellenen Maliyet boş olamaz.");

        if (updateMaintenanceDto.getMechanic().isEmpty())
            throw new RuntimeException("Güncellenen Mekanik veya servis sağlayıcısı boş olamaz.");

        if (updateMaintenanceDto.getNextMaintenanceDate() == null)
            throw new RuntimeException("Güncellenen Sonraki bakım tarihi boş olamaz.");

        updateMaintenance.setVehicle(vehicle);
        updateMaintenance.setMaintenanceDate(updateMaintenanceDto.getMaintenanceDate());
        updateMaintenance.setMaintenanceType(updateMaintenanceDto.getMaintenanceType());
        updateMaintenance.setCost(updateMaintenanceDto.getCost());
        updateMaintenance.setMechanic(updateMaintenanceDto.getMechanic());
        updateMaintenance.setNextMaintenanceDate(updateMaintenanceDto.getNextMaintenanceDate());
        updateMaintenance.setDescription(updateMaintenanceDto.getDescription());
        maintenanceRepository.save(updateMaintenance);
    }
    @Override
    public void delete(int id, String areYouSure) {
        Maintenance deleteMaintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz bakım kaydı bulunamıyor."));

        deleteMaintenance.setDeleteConfirmation(areYouSure);

        if (deleteMaintenance.isDeleteConfirmed()) {
            maintenanceRepository.delete(deleteMaintenance);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }
}
