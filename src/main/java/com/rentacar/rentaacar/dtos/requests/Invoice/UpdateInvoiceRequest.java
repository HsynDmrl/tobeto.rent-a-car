package com.rentacar.rentaacar.dtos.requests.Invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {
    private int orderId;
    private int customerId;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private double totalAmount;
    private String currency;
    private String status;
}