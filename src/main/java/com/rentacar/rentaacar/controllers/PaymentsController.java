package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.dtos.abstracts.PaymentService;
import com.rentacar.rentaacar.services.dtos.requests.Payment.AddPaymentRequest;
import com.rentacar.rentaacar.services.dtos.requests.Payment.UpdatePaymentRequest;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payments")
@AllArgsConstructor
public class PaymentsController {
    private final PaymentService paymentService;
    @GetMapping
    public List<GetPaymentListResponse> getAll() {
        return this.paymentService.getAll();
    }
    @GetMapping("{id}")
    public GetPaymentResponse getById(@PathVariable int id){
        return this.paymentService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddPaymentRequest addPaymentDto) {
        paymentService.add(addPaymentDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdatePaymentRequest updatePaymentDto){
        paymentService.update(id, updatePaymentDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        paymentService.delete(id, areYouSure);
    }
}
