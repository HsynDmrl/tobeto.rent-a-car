package com.rentacar.rentaacar.services.concretes;

import com.rentacar.rentaacar.entities.*;
import com.rentacar.rentaacar.repositories.InsuranceRepository;
import com.rentacar.rentaacar.repositories.VehicleRepository;
import com.rentacar.rentaacar.services.abstracts.InsuranceService;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.AddInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.UpdateInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InsuranceManager implements InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public List<GetInsuranceListResponse> getAll() {
        List<Insurance> insuranceList = insuranceRepository.findAll();
        List<GetInsuranceListResponse> getInsuranceDtoList = new ArrayList<>();
        if (insuranceList.isEmpty())
            throw new RuntimeException("Kayıtlı sigorta bulunmuyor.");

        for (Insurance insurance : insuranceList) {
            GetInsuranceListResponse dto = new GetInsuranceListResponse();
            dto.setInsuranceCompany(insurance.getInsuranceCompany());
            dto.setPolicyNumber(insurance.getPolicyNumber());
            dto.setExpirationDate(String.valueOf(insurance.getExpirationDate()));
            dto.setCoverageType(insurance.getCoverageType());
            dto.setPremiumAmount(String.valueOf(insurance.getPremiumAmount()));
            dto.setStatus(insurance.getStatus());
            getInsuranceDtoList.add(dto);
        }
        return getInsuranceDtoList;
    }

    @Override
    public GetInsuranceResponse getById(int id) {
        Insurance getByIdInsuranceDto = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sigorta bulunamadı."));

        GetInsuranceResponse getByIdInsurance = new GetInsuranceResponse();
        getByIdInsurance.setInsuranceCompany(getByIdInsuranceDto.getInsuranceCompany());
        getByIdInsurance.setPolicyNumber(getByIdInsuranceDto.getPolicyNumber());
        getByIdInsurance.setExpirationDate(String.valueOf(getByIdInsuranceDto.getExpirationDate()));
        getByIdInsurance.setCoverageType(getByIdInsuranceDto.getCoverageType());
        getByIdInsurance.setPremiumAmount(String.valueOf(getByIdInsuranceDto.getPremiumAmount()));
        getByIdInsurance.setStatus(getByIdInsuranceDto.getStatus());
        return getByIdInsurance;
    }

    @Override
    public void add(AddInsuranceRequest addInsuranceDto) {

        if (addInsuranceDto.getVehicleId() <= 0)
            throw new RuntimeException("Araç ID geçersiz.");

        if (addInsuranceDto.getInsuranceCompany().length() < 2)
            throw new RuntimeException("Sigorta şirketi şirketi 2 karakterden kısa olamaz.");

        if (addInsuranceDto.getPolicyNumber().length() < 2)
            throw new RuntimeException("Poliçe numarası 2 karakterden kısa olamaz.");

        if (addInsuranceDto.getExpirationDate() == null || addInsuranceDto.getExpirationDate().trim().isEmpty())
            throw new RuntimeException("Son kullanma tarihi boş olamaz.");

        if (addInsuranceDto.getCoverageType().length() < 2)
            throw new RuntimeException("Kapsam türü 2 karakterden kısa olamaz.");

        if (addInsuranceDto.getPremiumAmount() == null )
            throw new RuntimeException("Prim miktarı boş veya sıfır olamaz.");

        Insurance addInsurance = new Insurance();

        addInsurance.setInsuranceCompany(addInsuranceDto.getInsuranceCompany());
        addInsurance.setPolicyNumber(addInsuranceDto.getPolicyNumber());
        addInsurance.setExpirationDate(LocalDate.parse(addInsuranceDto.getExpirationDate()));
        addInsurance.setCoverageType(addInsuranceDto.getCoverageType());
        addInsurance.setPremiumAmount(Double.parseDouble(addInsuranceDto.getPremiumAmount()));
        addInsurance.setStatus(addInsuranceDto.getStatus());
        insuranceRepository.save(addInsurance);
    }

    @Override
    public void update(int id, UpdateInsuranceRequest updateInsuranceDto) {

        if (updateInsuranceDto.getVehicleId() <= 0)
            throw new RuntimeException("Araç ID geçersiz.");

        Insurance updateInsurance = insuranceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sigorta bulunamadı."));

        if (updateInsuranceDto.getInsuranceCompany().length() < 2)
            throw new RuntimeException("Güncellenen sigorta şirketi 2 karakterden kısa olamaz.");

        if (updateInsuranceDto.getPolicyNumber().isEmpty())
            throw new RuntimeException("Güncellenen poliçe numarası 2 karakterden kısa olamaz.");

        if (updateInsuranceDto.getExpirationDate() == null || updateInsuranceDto.getExpirationDate().trim().isEmpty())
            throw new RuntimeException("Güncellenen Son kullanma tarihi boş olamaz.");

        if (updateInsuranceDto.getCoverageType().length() < 2)
            throw new RuntimeException("Güncellenen Kapsam türü 2 karakterden kısa olamaz.");

        if (updateInsuranceDto.getPremiumAmount() == null)
            throw new RuntimeException("Güncellenen Prim miktarı boş veya sıfır olamaz.");

        updateInsurance.setInsuranceCompany(updateInsuranceDto.getInsuranceCompany());
        updateInsurance.setPolicyNumber(updateInsuranceDto.getPolicyNumber());
        updateInsurance.setExpirationDate(LocalDate.parse(updateInsuranceDto.getExpirationDate()));
        updateInsurance.setCoverageType(updateInsuranceDto.getCoverageType());
        updateInsurance.setPremiumAmount(Double.parseDouble(updateInsuranceDto.getPremiumAmount()));
        updateInsurance.setStatus(updateInsuranceDto.getStatus());
        insuranceRepository.save(updateInsurance);
    }

    @Override
    public void delete(int id, String areYouSure) {

        Insurance deleteInsurance = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz sigorta bulunamıyor."));

        deleteInsurance.setDeleteConfirmation(areYouSure);

        if (deleteInsurance.isDeleteConfirmed()) {
            insuranceRepository.delete(deleteInsurance);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }

    @Override
    public List<GetInsuranceListResponse> findByInsuranceCompany(String insuranceCompany) {

        List<Insurance> insurances = insuranceRepository.findByInsuranceCompany(insuranceCompany);
        List<GetInsuranceListResponse> response = new ArrayList<>();

        for (Insurance insurance : insurances) {
            response.add(new GetInsuranceListResponse(
                    insurance.getInsuranceCompany(),
                    insurance.getPolicyNumber(),
                    String.valueOf(insurance.getExpirationDate()),
                    insurance.getCoverageType(),
                    String.valueOf(insurance.getPremiumAmount()),
                    insurance.getStatus()));
        }
        return response;
    }

    @Override
    public List<GetInsuranceListResponse> findByPolicyNumberIsNot(String policyNumber) {

        List<Insurance> insurances = insuranceRepository.findByInsuranceCompany(policyNumber);
        List<GetInsuranceListResponse> response = new ArrayList<>();

        for (Insurance insurance : insurances) {
            response.add(new GetInsuranceListResponse(
                    insurance.getInsuranceCompany(),
                    insurance.getPolicyNumber(),
                    String.valueOf(insurance.getExpirationDate()),
                    insurance.getCoverageType(),
                    String.valueOf(insurance.getPremiumAmount()),
                    insurance.getStatus()));
        }
        return response;
    }

    @Override
    public List<GetInsuranceListResponse> getInsurancesByCoverageType(String coverageType) {
        return insuranceRepository.getInsurancesByCoverageType(coverageType);
    }

    @Override
    public List<GetInsuranceListResponse> getInsurancesWithPremiumGreaterThan(String premiumAmount) {
        return insuranceRepository.getInsurancesWithPremiumGreaterThan(premiumAmount);
    }
}
