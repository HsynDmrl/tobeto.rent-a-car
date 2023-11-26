package com.rentacar.rentaacar.services.dtos.concretes;

import com.rentacar.rentaacar.entities.*;
import com.rentacar.rentaacar.repositories.CustomerRepository;
import com.rentacar.rentaacar.repositories.InvoiceRepository;
import com.rentacar.rentaacar.repositories.OrderRepository;
import com.rentacar.rentaacar.services.dtos.abstracts.InvoiceService;
import com.rentacar.rentaacar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Invoice.GetInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<GetInvoiceListResponse> getAll() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<GetInvoiceListResponse> getInvoiceDtoList = new ArrayList<>();

        if (invoiceList.isEmpty())
            throw new RuntimeException("Kayıtlı fatura bulunmuyor.");

        for (Invoice invoice : invoiceList) {
            GetInvoiceListResponse dto = new GetInvoiceListResponse();
            dto.setInvoiceDate(invoice.getInvoiceDate());
            dto.setDueDate(invoice.getDueDate());
            dto.setTotalAmount(invoice.getTotalAmount());
            dto.setCurrency(invoice.getCurrency());
            dto.setStatus(invoice.getStatus());
            getInvoiceDtoList.add(dto);
        }
        return getInvoiceDtoList;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        Invoice getByIdInvoiceDto = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir fatura bulunamadı."));

        GetInvoiceResponse getByIdInvoice = new GetInvoiceResponse();
        getByIdInvoice.setInvoiceDate(getByIdInvoiceDto.getInvoiceDate());
        getByIdInvoice.setDueDate(getByIdInvoiceDto.getDueDate());
        getByIdInvoice.setTotalAmount(getByIdInvoiceDto.getTotalAmount());
        getByIdInvoice.setCurrency(getByIdInvoiceDto.getCurrency());
        getByIdInvoice.setStatus(getByIdInvoiceDto.getStatus());
        return getByIdInvoice;
    }

    @Override
    public void add(AddInvoiceRequest addInvoiceDto) {

        if (addInvoiceDto.getInvoiceDate() == null)
            throw new RuntimeException("Fatura tarihi boş olamaz.");

        if (addInvoiceDto.getDueDate() == null)
            throw new RuntimeException("Son ödeme tarihi boş olamaz.");

        if (addInvoiceDto.getTotalAmount() <= 0.0)
            throw new RuntimeException("Toplam miktar boş veya sıfır olamaz.");

        if (addInvoiceDto.getCurrency().isEmpty())
            throw new RuntimeException("Para birimi boş olamaz.");

        if (addInvoiceDto.getStatus().isEmpty())
            throw new RuntimeException("Durum boş olamaz.");

        Invoice addInvoice = new Invoice();

        Order order = orderRepository.findById(addInvoiceDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));
        addInvoice.setOrder(order);

        Customer customer = customerRepository.findById(addInvoiceDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir müşteri bulunamadı."));
        addInvoice.setCustomer(customer);

        addInvoice.setInvoiceDate(addInvoiceDto.getInvoiceDate());
        addInvoice.setDueDate(addInvoiceDto.getDueDate());
        addInvoice.setTotalAmount(addInvoiceDto.getTotalAmount());
        addInvoice.setCurrency(addInvoiceDto.getCurrency());
        addInvoice.setStatus(addInvoiceDto.getStatus());
        invoiceRepository.save(addInvoice);
    }

    @Override
    public void update(int id, UpdateInvoiceRequest updateInvoiceDto) {

        Invoice updateInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir fatura bulunamadı."));

        if (updateInvoiceDto.getInvoiceDate() == null)
            throw new RuntimeException("Güncellenen fatura tarihi boş olamaz.");

        if (updateInvoiceDto.getDueDate() == null)
            throw new RuntimeException("Güncellenen son ödeme tarihi boş olamaz.");

        if (updateInvoiceDto.getTotalAmount() <= 0.0)
            throw new RuntimeException("Güncellenen Toplam miktar boş veya sıfır olamaz.");

        if (updateInvoiceDto.getCurrency().isEmpty())
            throw new RuntimeException("Güncellenen para birimi boş olamaz.");

        if (updateInvoiceDto.getStatus().isEmpty())
            throw new RuntimeException("Güncellenen durum boş olamaz.");

        Order order = orderRepository.findById(updateInvoiceDto.getOrderId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));
        updateInvoice.setOrder(order);

        Customer customer = customerRepository.findById(updateInvoiceDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir müşteri bulunamadı."));
        updateInvoice.setCustomer(customer);

        updateInvoice.setInvoiceDate(updateInvoiceDto.getInvoiceDate());
        updateInvoice.setDueDate(updateInvoiceDto.getDueDate());
        updateInvoice.setTotalAmount(updateInvoiceDto.getTotalAmount());
        updateInvoice.setCurrency(updateInvoiceDto.getCurrency());
        updateInvoice.setStatus(updateInvoiceDto.getStatus());
        invoiceRepository.save(updateInvoice);
    }

    @Override
    public void delete(int id, String areYouSure) {
        Invoice deleteInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz fatura bulunamıyor."));

        deleteInvoice.setDeleteConfirmation(areYouSure);

        if (deleteInvoice.isDeleteConfirmed()) {
            invoiceRepository.delete(deleteInvoice);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }
}
