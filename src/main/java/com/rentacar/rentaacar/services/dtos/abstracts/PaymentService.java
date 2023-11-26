package com.rentacar.rentaacar.services.dtos.abstracts;

import com.rentacar.rentaacar.services.dtos.requests.Payment.AddPaymentRequest;
import com.rentacar.rentaacar.services.dtos.requests.Payment.UpdatePaymentRequest;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PaymentService {
    public List<GetPaymentListResponse> getAll();
    public GetPaymentResponse getById(@PathVariable int id);
    public void add(@RequestBody AddPaymentRequest addPaymentDto);
    public void update(@PathVariable int id, @RequestBody UpdatePaymentRequest updatePaymentDto);
    public void delete(@PathVariable int id, String areYouSure);
}
