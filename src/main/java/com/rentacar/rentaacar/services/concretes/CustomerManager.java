package com.rentacar.rentaacar.services.concretes;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.repositories.CustomerRepository;
import com.rentacar.rentaacar.services.abstracts.CustomerService;
import com.rentacar.rentaacar.services.dtos.requests.Customer.AddCustomerRequest;
import com.rentacar.rentaacar.services.dtos.requests.Customer.UpdateCustomerRequest;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public List<GetCustomerListResponse> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<GetCustomerListResponse> getCustomerDtoList = new ArrayList<>();

        if (customerList.isEmpty())
            throw new RuntimeException("Kayıtlı müşteri bulunmuyor.");

        for (Customer customer : customerList) {
            GetCustomerListResponse dto = new GetCustomerListResponse();
            dto.setName(customer.getName());
            dto.setSurname(customer.getSurname());
            dto.setAddress(customer.getAddress());
            dto.setCity(customer.getCity());
            dto.setPhone(customer.getPhone());
            dto.setLicenseNumber(customer.getLicenseNumber());
            dto.setTcNo(customer.getTcNo());
            getCustomerDtoList.add(dto);
        }
        return getCustomerDtoList;
    }
    @Override
    public GetCustomerResponse getById(int id) {
        Customer getByIdCustomerDto = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir müşteri bulunamadı."));

        GetCustomerResponse getByIdCustomer = new GetCustomerResponse();
        getByIdCustomer.setName(getByIdCustomerDto.getName());
        getByIdCustomer.setSurname(getByIdCustomerDto.getSurname());
        getByIdCustomer.setAddress(getByIdCustomerDto.getAddress());
        getByIdCustomer.setPhone(getByIdCustomerDto.getPhone());
        getByIdCustomer.setCity(getByIdCustomerDto.getCity());
        getByIdCustomer.setEmail(getByIdCustomerDto.getEmail());
        getByIdCustomer.setLicenseNumber(getByIdCustomerDto.getLicenseNumber());
        getByIdCustomer.setTcNo(getByIdCustomerDto.getTcNo());

        return getByIdCustomer;
    }
    @Override
    public void add(AddCustomerRequest addCustomerDto) {

        if(customerRepository.existsByTcNo(addCustomerDto.getTcNo().trim()))
            throw new RuntimeException("TC Numarası kaydı daha önceden bulunuyor, farklı TC numarası giriniz.");
        if(customerRepository.existsByEmail(addCustomerDto.getEmail().trim()))
            throw new RuntimeException("Email kaydı daha önceden bulunuyor, farklı email adresi giriniz.");

        Customer addCustomer = new Customer();
        addCustomer.setName(addCustomerDto.getName());
        addCustomer.setSurname(addCustomerDto.getSurname());
        addCustomer.setAddress(addCustomerDto.getAddress());
        addCustomer.setCity(addCustomerDto.getCity());
        addCustomer.setPhone(addCustomerDto.getPhone());
        addCustomer.setEmail(addCustomerDto.getEmail());
        addCustomer.setLicenseNumber(addCustomerDto.getLicenseNumber());
        addCustomer.setTcNo(addCustomerDto.getTcNo());

        customerRepository.save(addCustomer);
    }

    @Override
    public void update(int id, UpdateCustomerRequest updateCustomerDto) {
        Customer updateCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir müşteri bulunamadı."));

        if (updateCustomerDto.getName().length() < 2)
            throw new RuntimeException("Güncellenen ad 2 karakterden kısa olamaz.");

        if (updateCustomerDto.getSurname().length() < 2)
            throw new RuntimeException("Güncellenen soyad 2 karakterden kısa olamaz.");

        if (updateCustomerDto.getAddress().length() < 10)
            throw new RuntimeException("Güncellenen Adres 10 karakterden kısa olamaz.");

        if (updateCustomerDto.getCity().length() < 3)
            throw new RuntimeException("Güncellenen Şehir 3 karakterden kısa olamaz.");

        if (updateCustomerDto.getEmail().length() < 4)
            throw new RuntimeException("Güncellenen E-Posta Adresi 4 karakterden kısa olamaz.");

        if (updateCustomerDto.getPhone().length() < 11)
            throw new RuntimeException("Güncellenen Telefon Numarası 11 karakterden kısa olamaz.");

        if (updateCustomerDto.getLicenseNumber().length() < 6)
            throw new RuntimeException("Güncellenen Ehliyet numarası 6 karakterden kısa olamaz.");

        if (updateCustomerDto.getTcNo().length() != 11)
            throw new RuntimeException("Güncellenen TC Kimlik Numarası 11 haneli olmalıdır.");

        updateCustomer.setName(updateCustomerDto.getName());
        updateCustomer.setSurname(updateCustomerDto.getSurname());
        updateCustomer.setAddress(updateCustomerDto.getAddress());
        updateCustomer.setCity(updateCustomerDto.getCity());
        updateCustomer.setEmail(updateCustomerDto.getEmail());
        updateCustomer.setPhone(updateCustomerDto.getPhone());
        updateCustomer.setLicenseNumber(updateCustomerDto.getLicenseNumber());
        updateCustomer.setTcNo(updateCustomerDto.getTcNo());
        customerRepository.save(updateCustomer);
    }

    @Override
    public void delete(int id, String areYouSure) {
        Customer deleteCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz müşteri bulunamıyor."));

        deleteCustomer.setDeleteConfirmation(areYouSure);

        if (deleteCustomer.isDeleteConfirmed()) {
            customerRepository.delete(deleteCustomer);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }
    @Override
    public List<GetCustomerListResponse> findByName(String name) {

        List<Customer> customers = customerRepository.findByName(name);
        List<GetCustomerListResponse> response = new ArrayList<>();

        for (Customer customer : customers) {
            response.add(new GetCustomerListResponse(
                    customer.getName(),
                    customer.getSurname(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getPhone(),
                    customer.getEmail(),
                    customer.getLicenseNumber(),
                    customer.getTcNo()));
        }
        return response;
    }

    @Override
    public List<GetCustomerListResponse> findByNameIsNot(String name) {
        List<Customer> customers = customerRepository.findByNameIsNot(name);
        List<GetCustomerListResponse> response = new ArrayList<>();

        for (Customer customer : customers) {
            response.add(new GetCustomerListResponse(
                    customer.getName(),
                    customer.getSurname(),
                    customer.getAddress(),
                    customer.getCity(),
                    customer.getPhone(),
                    customer.getEmail(),
                    customer.getLicenseNumber(),
                    customer.getTcNo()));
        }
        return response;
    }

    @Override
    public List<GetCustomerListResponse> getCustomerFromCity(String city) {
        return customerRepository.getCustomerFromCity(city);
    }

    @Override
    public List<GetCustomerListResponse> getCustomersWithSurnameStartingWithLetter(String startLetter) {
        return customerRepository.getCustomersWithSurnameStartingWithLetter(startLetter);
    }
}
