package com.rentacar.rentaacar.dtos.requests.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
    private int orderId;
    private LocalDate paymentDate;
    private String paymentMethod;
    private double amount;
    private String currency;
    private String transactionNo;
    private int status;
}