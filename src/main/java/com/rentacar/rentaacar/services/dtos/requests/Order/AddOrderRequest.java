package com.rentacar.rentaacar.services.dtos.requests.Order;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
    private int customerId;
    private int invoiceId;
    private LocalDate pickUpDate;
    private LocalDate dropDate;
    private int vehicleId;
    private boolean delivered;
    private String notes;
    private double totalCost;
    private int employeeId;
}
