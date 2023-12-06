package com.rentacar.rentaacar.services.concretes;

import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Order;
import com.rentacar.rentaacar.entities.Payment;
import com.rentacar.rentaacar.repositories.OrderRepository;
import com.rentacar.rentaacar.repositories.PaymentRepository;
import com.rentacar.rentaacar.services.abstracts.PaymentService;
import com.rentacar.rentaacar.services.dtos.requests.Payment.AddPaymentRequest;
import com.rentacar.rentaacar.services.dtos.requests.Payment.UpdatePaymentRequest;
import com.rentacar.rentaacar.services.dtos.responses.Customer.GetCustomerListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Payment.GetPaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class PaymentManager implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    @Override
    public List<GetPaymentListResponse> getAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        List<GetPaymentListResponse> getPaymentDtoList = new ArrayList<>();

        if (paymentList.isEmpty())
            throw new RuntimeException("Kayıtlı ödeme bulunmuyor.");

        for (Payment payment : paymentList) {
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
    @Override
    public GetPaymentResponse getById(int id) {
        Payment getByIdPaymentDto = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir ödeme bulunamadı."));

        GetPaymentResponse getByIdPayment = new GetPaymentResponse();
        getByIdPayment.setPaymentDate(getByIdPaymentDto.getPaymentDate());
        getByIdPayment.setPaymentMethod(getByIdPaymentDto.getPaymentMethod());
        getByIdPayment.setAmount(getByIdPaymentDto.getAmount());
        getByIdPayment.setCurrency(getByIdPaymentDto.getCurrency());
        getByIdPayment.setTransactionNo(getByIdPaymentDto.getTransactionNo());
        getByIdPayment.setStatus(getByIdPaymentDto.getStatus());
        return getByIdPayment;
    }
    @Override
    public void add(AddPaymentRequest addPaymentDto) {

        Order order = orderRepository.findById(addPaymentDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));

        if (order.getDropDate().isAfter(addPaymentDto.getPaymentDate()))
            throw new RuntimeException("Bu ID ile kayıtlı siparişin teslim alma tarihi, ödeme tarihinden önce olamaz.");

        if (addPaymentDto.getPaymentMethod().isEmpty())
            throw new RuntimeException("Ödeme yöntemi geçerli değil.");

        if (addPaymentDto.getAmount() <= 0.0)
            throw new RuntimeException("Ödeme tutarı geçerli değil.");

        if (addPaymentDto.getCurrency().isEmpty())
            throw new RuntimeException("Ödeme para birimi geçerli değil.");

        if (addPaymentDto.getTransactionNo().isEmpty())
            throw new RuntimeException("Ödeme işlem numarası geçerli değil.");

        if (addPaymentDto.getStatus() != 1 && addPaymentDto.getStatus() != 0)
            throw new RuntimeException("Ödeme durumu geçerli değil. Geçerli değerler: 0 (Onaysız) veya 1 (Onaylı).");

        Payment addPayment = new Payment();
        addPayment.setOrder(order);

        addPayment.setPaymentDate(addPaymentDto.getPaymentDate());
        addPayment.setPaymentMethod(addPaymentDto.getPaymentMethod());
        addPayment.setAmount(addPaymentDto.getAmount());
        addPayment.setCurrency(addPaymentDto.getCurrency());
        addPayment.setTransactionNo(addPaymentDto.getTransactionNo());
        addPayment.setStatus(addPaymentDto.getStatus());

        paymentRepository.save(addPayment);
    }
    @Override
    public void update(int id, UpdatePaymentRequest updatePaymentDto) {

        if (updatePaymentDto.getOrderId() <= 0)
            throw new RuntimeException("Sipariş ID geçersiz.");

        Payment updatePayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));

        Order order = orderRepository.findById(updatePaymentDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));

        if (order.getDropDate().isAfter(updatePaymentDto.getPaymentDate()))
            throw new RuntimeException("Bu ID ile kayıtlı siparişin teslim alma tarihi, güncellenen ödeme tarihinden önce olamaz.");

        if (updatePaymentDto.getPaymentMethod() == null)
            throw new RuntimeException("Güncellenen Ödeme yöntemi geçerli değil.");

        if (updatePaymentDto.getAmount() <= 0.0)
            throw new RuntimeException("Güncellenen Ödeme tutarı geçerli değil.");

        if (updatePaymentDto.getCurrency() == null)
            throw new RuntimeException("Güncellenen Ödeme para birimi geçerli değil.");

        if (updatePaymentDto.getTransactionNo() == null || updatePaymentDto.getTransactionNo().isEmpty())
            throw new RuntimeException("Güncellenen Ödeme işlem numarası geçerli değil.");

        if (updatePaymentDto.getStatus() != 1 && updatePaymentDto.getStatus() != 0)
            throw new RuntimeException("Güncellenen Ödeme durumu geçerli değil. Geçerli değerler: 0 (Onaysız) veya 1 (Onaylı).");

        updatePayment.setOrder(order);
        updatePayment.setPaymentDate(updatePaymentDto.getPaymentDate());
        updatePayment.setPaymentMethod(updatePaymentDto.getPaymentMethod());
        updatePayment.setAmount(updatePaymentDto.getAmount());
        updatePayment.setCurrency(updatePaymentDto.getCurrency());
        updatePayment.setTransactionNo(updatePaymentDto.getTransactionNo());
        updatePayment.setStatus(updatePaymentDto.getStatus());
        paymentRepository.save(updatePayment);
    }
    @Override
    public void delete(int id, String areYouSure) {
        Payment deletePayment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz sipariş bulunamıyor."));

        deletePayment.setDeleteConfirmation(areYouSure);

        if (deletePayment.isDeleteConfirmed()) {
            paymentRepository.delete(deletePayment);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }

    @Override
    public List<GetPaymentListResponse> findByAmount(double amount) {

        List<Payment> payments = paymentRepository.findByAmount(amount);
        List<GetPaymentListResponse> response = new ArrayList<>();

        for (Payment payment : payments) {
            response.add(new GetPaymentListResponse(
                    payment.getPaymentDate(),
                    payment.getPaymentMethod(),
                    payment.getAmount(),
                    payment.getCurrency(),
                    payment.getTransactionNo(),
                    payment.getStatus()));
        }
        return response;
    }

    @Override
    public List<GetPaymentListResponse> findByCurrency(String currency) {

        List<Payment> payments = paymentRepository.findByCurrency(currency);
        List<GetPaymentListResponse> response = new ArrayList<>();

        for (Payment payment : payments) {
            response.add(new GetPaymentListResponse(
                    payment.getPaymentDate(),
                    payment.getPaymentMethod(),
                    payment.getAmount(),
                    payment.getCurrency(),
                    payment.getTransactionNo(),
                    payment.getStatus()));
        }
        return response;
    }

    @Override
    public List<GetPaymentListResponse> getPaymentsByStatus(int status) {
        return paymentRepository.getPaymentsByStatus(status);
    }

    @Override
    public List<GetPaymentListResponse> getPaymentByTransactionNo(String transactionNo) {
        return paymentRepository.getPaymentByTransactionNo(transactionNo);
    }
}
