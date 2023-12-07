package com.rentacar.rentaacar.services.dtos.requests.Order;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.entities.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {

    @NotNull(message = "Müşteri ID geçersiz.")
    private int customerId;

    @NotNull(message = "Fatura ID geçersiz.")
    private int invoiceId;

    @NotNull(message = "Teslim tarihi tarihi boş olamaz.")
    private LocalDate pickUpDate;

    @NotNull(message = "Alım tarihi boş olamaz.")
    private LocalDate dropDate;

    @NotNull(message = "Araç ID geçersiz.")
    private int vehicleId;

    @NotNull(message = "Teslim bilgisi geçersiz.")
    private boolean delivered;

    @NotNull(message = "Notlar boş olamaz.")
    private String notes;

    @NotNull(message = "Toplam tutar geçersiz.")
    private double totalCost;

    @NotNull(message = "Çalışan ID geçersiz.")
    private int employeeId;
}
