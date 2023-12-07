package com.rentacar.rentaacar.services.concretes;

import com.rentacar.rentaacar.entities.*;
import com.rentacar.rentaacar.repositories.InsuranceRepository;
import com.rentacar.rentaacar.repositories.MaintenanceRepository;
import com.rentacar.rentaacar.repositories.VehicleRepository;
import com.rentacar.rentaacar.services.abstracts.VehicleService;
import com.rentacar.rentaacar.services.dtos.requests.Vehicle.AddVehicleRequest;
import com.rentacar.rentaacar.services.dtos.requests.Vehicle.UpdateVehicleRequest;
import com.rentacar.rentaacar.services.dtos.responses.Vehicle.GetVehicleListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Vehicle.GetVehicleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VehicleManager implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final InsuranceRepository insuranceRepository;
    private final MaintenanceRepository maintenanceRepository;
    @Override
    public List<GetVehicleListResponse> getAll() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        List<GetVehicleListResponse> getVehicleDtoList = new ArrayList<>();

        if (vehicleList.isEmpty())
            throw new RuntimeException("Kayıtlı araba bulunmuyor.");

        for (Vehicle vehicle : vehicleList) {
            GetVehicleListResponse dto = new GetVehicleListResponse();
            dto.setBrand(vehicle.getBrand());
            dto.setModel(vehicle.getModel());
            dto.setYear(vehicle.getYear());
            dto.setColor(vehicle.getColor());
            dto.setDailyCost(vehicle.getDailyCost());
            dto.setStatus(vehicle.getStatus());
            dto.setPlateNumber(vehicle.getPlateNumber());
            dto.setOdometer(vehicle.getOdometer());
            dto.setFuelType(vehicle.getFuelType());
            dto.setNotes(vehicle.getNotes());
            getVehicleDtoList.add(dto);
        }
        return getVehicleDtoList;
    }
    @Override
    public GetVehicleResponse getById(int id) {

        Vehicle getByIdVehicleDto = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir araba bulunamadı."));

        GetVehicleResponse getByIdVehicle = new GetVehicleResponse();
        getByIdVehicle.setBrand(getByIdVehicleDto.getBrand());
        getByIdVehicle.setModel(getByIdVehicleDto.getModel());
        getByIdVehicle.setYear(getByIdVehicleDto.getYear());
        getByIdVehicle.setColor(getByIdVehicleDto.getColor());
        getByIdVehicle.setDailyCost(getByIdVehicleDto.getDailyCost());
        getByIdVehicle.setStatus(getByIdVehicleDto.getStatus());
        getByIdVehicle.setPlateNumber(getByIdVehicleDto.getPlateNumber());
        getByIdVehicle.setOdometer(getByIdVehicleDto.getOdometer());
        getByIdVehicle.setFuelType(getByIdVehicleDto.getFuelType());
        getByIdVehicle.setNotes(getByIdVehicleDto.getNotes());
        return getByIdVehicle;
    }
    @Override
    public void add(AddVehicleRequest addVehicleDto) {

        if (vehicleRepository.existsByPlateNumber(addVehicleDto.getPlateNumber().trim())) {
            throw new RuntimeException("Araç plaka numarası kaydı daha önceden bulunuyor, lütfen farklı bir plaka numarası giriniz.");
        }

        Vehicle addVehicle = new Vehicle();
        addVehicle.setBrand(addVehicleDto.getBrand());
        addVehicle.setModel(addVehicleDto.getModel());
        addVehicle.setYear(addVehicleDto.getYear());
        addVehicle.setColor(addVehicleDto.getColor());
        addVehicle.setDailyCost(addVehicleDto.getDailyCost());
        addVehicle.setStatus(addVehicleDto.getStatus());
        addVehicle.setPlateNumber(addVehicleDto.getPlateNumber());
        addVehicle.setOdometer(addVehicleDto.getOdometer());
        addVehicle.setFuelType(addVehicleDto.getFuelType());
        addVehicle.setNotes(addVehicleDto.getNotes());

        Insurance insurance = insuranceRepository.findById(addVehicleDto.getInsoranceId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sigorta bulunamadı."));
        addVehicle.setInsurance(insurance);

        Maintenance maintenance = maintenanceRepository.findById(addVehicleDto.getMaintenanceId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile bir bakım kaydı bulunamadı."));
        addVehicle.setMaintenance(maintenance);
        vehicleRepository.save(addVehicle);
    }
    @Override
    public void update(int id, UpdateVehicleRequest updateVehicleDto) {

        Vehicle updateVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir araba bulunamadı."));

        if (updateVehicleDto.getBrand().length() < 2)
            throw new RuntimeException("Güncellenen Marka bilgisi 2 karakterden kısa olamaz.");

        if (updateVehicleDto.getModel().length() < 2)
            throw new RuntimeException("Güncellenen Model bilgisi 2 karakterden kısa olamaz.");

        if (Integer.toString(updateVehicleDto.getYear()).length() != 4)
            throw new RuntimeException("Güncellenen Yıl bilgisi geçersiz. Lütfen geçerli bir yıl giriniz.");

        if (updateVehicleDto.getColor().length() < 2)
            throw new RuntimeException("Güncellenen Renk bilgisi geçersiz. En az 2 karakterden oluşmalıdır.");

        if (updateVehicleDto.getDailyCost() <= 0)
            throw new RuntimeException("Güncellenen Günlük kira bedeli geçersiz.");

        if (updateVehicleDto.getStatus().isEmpty())
            throw new RuntimeException("Güncellenen Durum bilgisi boş olamaz");

        if (updateVehicleDto.getPlateNumber() == null || updateVehicleDto.getPlateNumber().length() > 8)
            throw new RuntimeException("Güncellenen Plaka numarası boş olamaz ve en fazla 7 karakterden uzun olamaz.");

        if (updateVehicleDto.getOdometer() <= 0)
            throw new RuntimeException("Güncellenen Araç kilometre bilgisi geçerli değil.");

        if (updateVehicleDto.getFuelType().isEmpty())
            throw new RuntimeException("Güncellenen Yakıt türü bilgisi geçerli değil.");


        updateVehicle.setBrand(updateVehicleDto.getBrand());
        updateVehicle.setModel(updateVehicleDto.getModel());
        updateVehicle.setYear(updateVehicleDto.getYear());
        updateVehicle.setColor(updateVehicleDto.getColor());
        updateVehicle.setDailyCost(updateVehicleDto.getDailyCost());
        updateVehicle.setStatus(updateVehicleDto.getStatus());
        updateVehicle.setPlateNumber(updateVehicleDto.getPlateNumber());
        updateVehicle.setOdometer(updateVehicleDto.getOdometer());
        updateVehicle.setFuelType(updateVehicleDto.getFuelType());
        updateVehicle.setNotes(updateVehicleDto.getNotes());

        Insurance insurance = insuranceRepository.findById(updateVehicleDto.getInsoranceId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sigorta bulunamadı."));
        updateVehicle.setInsurance(insurance);

        Maintenance maintenance = maintenanceRepository.findById(updateVehicleDto.getMaintenanceId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile bir bakım kaydı bulunamadı."));
        updateVehicle.setMaintenance(maintenance);

        vehicleRepository.save(updateVehicle);
    }
    @Override
    public void delete(int id, String areYouSure) {
        Vehicle deleteVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz araba bulunamıyor."));

        deleteVehicle.setDeleteConfirmation(areYouSure);

        if (deleteVehicle.isDeleteConfirmed()) {
            vehicleRepository.delete(deleteVehicle);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }
}
