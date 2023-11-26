package com.rentacar.rentaacar.services.dtos.responses.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderListResponse {
    private LocalDate pickUpDate;
    private LocalDate dropDate;
    private boolean delivered;
    private String notes;
    private double totalCost;
}
