package com.rentacar.rentaacar.services.dtos.requests.Payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPaymentRequest {

    @NotBlank(message = "Sipariş ID geçersiz.")
    @NotNull
    private int orderId;

    @NotBlank(message = "Fatura tarihi boş olamaz.")
    @NotNull
    private LocalDate paymentDate;

    @NotBlank(message = "Ödeme yöntemi geçerli değil.")
    @NotNull
    private String paymentMethod;

    @NotBlank(message = "Ödeme tutarı geçerli değil.")
    @NotNull
    private double amount;

    @NotBlank(message = "Ödeme para birimi geçerli değil.")
    @NotNull
    private String currency;

    @NotBlank(message = "Ödeme işlem numarası geçerli değil.")
    @NotNull
    private String transactionNo;

    @NotBlank(message = "Durum bilgisi boş olamaz.")
    @NotNull
    private int status;
}
