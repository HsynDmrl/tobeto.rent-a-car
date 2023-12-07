package com.rentacar.rentaacar.services.dtos.requests.Invoice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInvoiceRequest {

    @NotBlank(message = "Müşteri ID geçersiz.")
    @NotNull
    private int customerId;

    @NotBlank(message = "Fatura tarihi boş olamaz.")
    @NotNull
    private LocalDate invoiceDate;

    @NotBlank(message = "Son ödeme tarihi boş olamaz.")
    @NotNull
    private LocalDate dueDate;

    @NotBlank(message = "Toplam miktar boş veya sıfır olamaz.")
    @NotNull
    private double totalAmount;

    @NotBlank(message = "Para birimi boş olamaz.")
    @NotNull
    private String currency;

    @NotBlank(message = "Durum boş olamaz.")
    @NotNull
    private String status;
}