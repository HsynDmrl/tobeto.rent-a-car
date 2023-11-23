package com.rentacar.rentaacar.dtos.responses.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {
    private LocalDate pickUpDate;
    private LocalDate dropDate;
    private boolean delivered;
    private String notes;
    private double totalCost;
}