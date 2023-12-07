package com.rentacar.rentaacar.services.abstracts;

import com.rentacar.rentaacar.entities.Payment;
import com.rentacar.rentaacar.services.dtos.requests.Payment.AddPaymentRequest;
import com.rentacar.rentaacar.services.dtos.requests.Payment.UpdatePaymentRequest;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaymentService {
    public List<GetPaymentListResponse> getAll();
    public GetPaymentResponse getById(@PathVariable int id);
    public void add(AddPaymentRequest addPaymentDto);
    public void update(@PathVariable int id, @RequestBody UpdatePaymentRequest updatePaymentDto);
    public void delete(@PathVariable int id, String areYouSure);
    List<GetPaymentListResponse> findByAmount(double amount);
    List<GetPaymentListResponse> findByCurrency(String currency);
    List<GetPaymentListResponse> getPaymentsByStatus(int status);
    List<GetPaymentListResponse> getPaymentByTransactionNo(String transactionNo);
}
