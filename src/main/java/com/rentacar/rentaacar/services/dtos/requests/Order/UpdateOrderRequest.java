package com.rentacar.rentaacar.services.dtos.requests.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {
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