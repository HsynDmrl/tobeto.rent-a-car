package com.rentacar.rentaacar.services.dtos.concretes;

import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.entities.Insurance;
import com.rentacar.rentaacar.entities.Invoice;
import com.rentacar.rentaacar.entities.Payment;
import com.rentacar.rentaacar.repositories.EmployeeRepository;
import com.rentacar.rentaacar.services.dtos.abstracts.EmployeeService;
import com.rentacar.rentaacar.services.dtos.requests.Employee.AddEmployeeRequest;
import com.rentacar.rentaacar.services.dtos.requests.Employee.UpdateEmployeeRequest;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Employee.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public List<GetEmployeeListResponse> getAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<GetEmployeeListResponse> getEmployeeDtoList = new ArrayList<>();

        if (employeeList.isEmpty())
            throw new RuntimeException("Kayıtlı çalışan bulunmuyor.");

        for (Employee employee : employeeList) {
            GetEmployeeListResponse dto = new GetEmployeeListResponse();
            dto.setName(employee.getName());
            dto.setSurname(employee.getSurname());
            dto.setTitle(employee.getTitle());
            dto.setEmail(employee.getEmail());
            dto.setPhone(employee.getPhone());
            dto.setHireDate(employee.getHireDate());
            dto.setAddress(employee.getAddress());
            dto.setCity(employee.getCity());
            dto.setSalary(employee.getSalary());
            dto.setTcNo(employee.getTcNo());
            getEmployeeDtoList.add(dto);
        }
        return getEmployeeDtoList;
    }
    @Override
    public GetEmployeeResponse getById(int id) {
        Employee getByIdEmployeeDto = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir çalışan bulunamadı."));

        GetEmployeeResponse getByIdEmployee = new GetEmployeeResponse();
        getByIdEmployee.setName(getByIdEmployeeDto.getName());
        getByIdEmployee.setSurname(getByIdEmployeeDto.getSurname());
        getByIdEmployee.setTitle(getByIdEmployeeDto.getTitle());
        getByIdEmployee.setEmail(getByIdEmployeeDto.getEmail());
        getByIdEmployee.setPhone(getByIdEmployeeDto.getPhone());
        getByIdEmployee.setHireDate(getByIdEmployeeDto.getHireDate());
        getByIdEmployee.setAddress(getByIdEmployeeDto.getAddress());
        getByIdEmployee.setCity(getByIdEmployeeDto.getCity());
        getByIdEmployee.setSalary(getByIdEmployeeDto.getSalary());
        getByIdEmployee.setTcNo(getByIdEmployeeDto.getTcNo());
        return getByIdEmployee;
    }
    @Override
    public void add(AddEmployeeRequest addEmployeeDto) {
        if (addEmployeeDto.getName().length() < 2)
            throw new RuntimeException("Ad 2 karakterden kısa olamaz.");

        if (addEmployeeDto.getSurname().length() < 2)
            throw new RuntimeException("Soyad 2 karakterden kısa olamaz.");

        if (addEmployeeDto.getTitle().length() < 3)
            throw new RuntimeException("Ünvan 3 karakterden kısa olamaz.");

        if (addEmployeeDto.getEmail().length() < 4)
            throw new RuntimeException("E-Posta Adresi 4 karakterden kısa olamaz.");

        if (addEmployeeDto.getPhone().length() < 11)
            throw new RuntimeException("Telefon Numarası 11 karakterden kısa olamaz.");

        if (addEmployeeDto.getAddress().length() < 10)
            throw new RuntimeException("Adres 10 karakterden kısa olamaz.");

        if (addEmployeeDto.getCity().length() < 3)
            throw new RuntimeException("Şehir 3 karakterden kısa olamaz.");

        if (addEmployeeDto.getSalary() <= 0)
            throw new RuntimeException("Maaş 0 olamaz.");

        if (addEmployeeDto.getTcNo().length() != 11)
            throw new RuntimeException("TC Kimlik Numarası 11 haneli olmalıdır.");

        Employee addEmployee = new Employee();
        addEmployee.setName(addEmployeeDto.getName());
        addEmployee.setSurname(addEmployeeDto.getSurname());
        addEmployee.setTitle(addEmployeeDto.getTitle());
        addEmployee.setEmail(addEmployeeDto.getEmail());
        addEmployee.setPhone(addEmployeeDto.getPhone());
        addEmployee.setHireDate(addEmployeeDto.getHireDate());
        addEmployee.setAddress(addEmployeeDto.getAddress());
        addEmployee.setCity(addEmployeeDto.getCity());
        addEmployee.setSalary(addEmployeeDto.getSalary());
        addEmployee.setTcNo(addEmployeeDto.getTcNo());
        employeeRepository.save(addEmployee);
    }
    @Override
    public void update(int id, UpdateEmployeeRequest updateEmployeeDto) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir çalışan bulunamadı."));

        if (updateEmployeeDto.getName().length() < 2)
            throw new RuntimeException("Güncellenen Ad 2 karakterden kısa olamaz.");

        if (updateEmployeeDto.getSurname().length() < 2)
            throw new RuntimeException("Güncellenen Soyad 2 karakterden kısa olamaz.");

        if (updateEmployeeDto.getTitle().length() < 3)
            throw new RuntimeException("Güncellenen Ünvan 3 karakterden kısa olamaz.");

        if (updateEmployeeDto.getEmail().length() < 4)
            throw new RuntimeException("Güncellenen E-Posta Adresi 4 karakterden kısa olamaz.");

        if (updateEmployeeDto.getPhone().length() < 11)
            throw new RuntimeException("Güncellenen Telefon Numarası 11 karakterden kısa olamaz.");

        if (updateEmployeeDto.getAddress().length() < 10)
            throw new RuntimeException("Güncellenen Adres 10 karakterden kısa olamaz.");

        if (updateEmployeeDto.getCity().length() < 3)
            throw new RuntimeException("Güncellenen Şehir 3 karakterden kısa olamaz.");

        if (updateEmployeeDto.getSalary() == 0.0)
            throw new RuntimeException("Güncellenen Maaş 0 olamaz.");

        if (updateEmployeeDto.getTcNo().length() != 11)
            throw new RuntimeException("Güncellenen TC Kimlik Numarası 11 haneli olmalıdır.");

        updateEmployee.setName(updateEmployeeDto.getName());
        updateEmployee.setSurname(updateEmployeeDto.getSurname());
        updateEmployee.setTitle(updateEmployeeDto.getTitle());
        updateEmployee.setEmail(updateEmployeeDto.getEmail());
        updateEmployee.setPhone(updateEmployeeDto.getPhone());
        updateEmployee.setHireDate(updateEmployeeDto.getHireDate());
        updateEmployee.setAddress(updateEmployeeDto.getAddress());
        updateEmployee.setCity(updateEmployeeDto.getCity());
        updateEmployee.setSalary(updateEmployeeDto.getSalary());
        updateEmployee.setTcNo(updateEmployeeDto.getTcNo());
        employeeRepository.save(updateEmployee);
    }
    @Override
    public void delete(int id, String areYouSure) {
        Employee deleteEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz çalışan bulunamıyor."));

        deleteEmployee.setDeleteConfirmation(areYouSure);

        if (deleteEmployee.isDeleteConfirmed()) {
            employeeRepository.delete(deleteEmployee);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }
}
