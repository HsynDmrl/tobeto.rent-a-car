package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Payment.AddPaymentRequest;
import com.rentacar.rentaacar.dtos.requests.Payment.UpdatePaymentRequest;
import com.rentacar.rentaacar.dtos.responses.Order.GetOrderListResponse;
import com.rentacar.rentaacar.dtos.responses.Payment.GetPaymentListResponse;
import com.rentacar.rentaacar.dtos.responses.Payment.GetPaymentResponse;
import com.rentacar.rentaacar.entities.Payment;
import com.rentacar.rentaacar.repositories.*;
import org.springframework.web.bind.annotation.*;
import com.rentacar.rentaacar.entities.Order;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/payments")
public class PaymentsController {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentsController(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public List<GetPaymentListResponse> getAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        List<GetPaymentListResponse> getPaymentDtoList = new ArrayList<>();
        for (int i = 0; i < paymentList.size(); i++) {
            Payment payment = paymentList.get(i);

            GetPaymentListResponse dto = new GetPaymentListResponse();
            dto.setPaymentDate(payment.getPaymentDate());
            dto.setPaymentMethod(payment.getPaymentMethod());
            dto.setAmount(payment.getAmount());
            dto.setCurrency(payment.getCurrency());
            dto.setTransactionNo(payment.getTransactionNo());
            dto.setStatus(payment.getStatus());
            getPaymentDtoList.add(dto);
        }
        return getPaymentDtoList;
    }
    @GetMapping("{id}")
    public GetPaymentResponse getById(@PathVariable int id){
        Payment getByIdPaymentDto = paymentRepository.findById(id).orElseThrow();
        GetPaymentResponse getByIdPayment = new GetPaymentResponse();
        getByIdPayment.setPaymentDate(getByIdPaymentDto.getPaymentDate());
        getByIdPayment.setPaymentMethod(getByIdPaymentDto.getPaymentMethod());
        getByIdPayment.setAmount(getByIdPaymentDto.getAmount());
        getByIdPayment.setCurrency(getByIdPaymentDto.getCurrency());
        getByIdPayment.setTransactionNo(getByIdPaymentDto.getTransactionNo());
        getByIdPayment.setStatus(getByIdPaymentDto.getStatus());

        return getByIdPayment;
    }
    @PostMapping
    public void add(@RequestBody AddPaymentRequest addPaymentDto) {
        Payment addPayment = new Payment();
        Order order = orderRepository.findById(addPaymentDto.getOrderId()).orElseThrow();
        addPayment.setOrder(order);
        addPayment.setPaymentDate(addPaymentDto.getPaymentDate());
        addPayment.setPaymentMethod(addPaymentDto.getPaymentMethod());
        addPayment.setAmount(addPaymentDto.getAmount());
        addPayment.setCurrency(addPaymentDto.getCurrency());
        addPayment.setTransactionNo(addPaymentDto.getTransactionNo());
        addPayment.setStatus(addPaymentDto.getStatus());
        paymentRepository.save(addPayment);
    }


    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdatePaymentRequest updatePaymentDto){
        Payment updatePayment = paymentRepository.findById(id).orElseThrow();
        Order order = orderRepository.findById(updatePaymentDto.getOrderId()).orElseThrow();
        updatePayment.setOrder(order);
        updatePayment.setPaymentDate(updatePaymentDto.getPaymentDate());
        updatePayment.setPaymentMethod(updatePaymentDto.getPaymentMethod());
        updatePayment.setAmount(updatePaymentDto.getAmount());
        updatePayment.setCurrency(updatePaymentDto.getCurrency());
        updatePayment.setTransactionNo(updatePaymentDto.getTransactionNo());
        updatePayment.setStatus(updatePaymentDto.getStatus());
        paymentRepository.save(updatePayment);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Payment deletePayment = paymentRepository.findById(id).orElseThrow();
        paymentRepository.delete(deletePayment);
    }
}
