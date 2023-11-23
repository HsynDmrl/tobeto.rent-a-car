package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentacar.rentaacar.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentacar.rentaacar.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentacar.rentaacar.dtos.responses.Invoice.GetInvoiceResponse;
import com.rentacar.rentaacar.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Invoice;
import com.rentacar.rentaacar.entities.Invoice;
import com.rentacar.rentaacar.entities.Order;
import com.rentacar.rentaacar.repositories.CustomerRepository;
import com.rentacar.rentaacar.repositories.InvoiceRepository;
import com.rentacar.rentaacar.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoicesController {
    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    
    public InvoicesController(InvoiceRepository invoiceRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        
    }
    @GetMapping
    public List<GetInvoiceListResponse> getAll() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<GetInvoiceListResponse> getInvoiceDtoList = new ArrayList<>();
        for (int i = 0; i < invoiceList.size(); i++) {
            Invoice invoice = invoiceList.get(i);

            GetInvoiceListResponse dto = new GetInvoiceListResponse();
            dto.setInvoiceDate(invoice.getInvoiceDate());
            dto.setInvoiceDate(invoice.getInvoiceDate());
            dto.setDueDate(invoice.getDueDate());
            dto.setTotalAmount(invoice.getTotalAmount());
            dto.setCurrency(invoice.getCurrency());
            dto.setStatus(invoice.getStatus());
            getInvoiceDtoList.add(dto);
        }
        return getInvoiceDtoList;
    }
    @GetMapping("{id}")
    public GetInvoiceResponse getById(@PathVariable int id){
        Invoice getByIdInvoiceDto = invoiceRepository.findById(id).orElseThrow();
        GetInvoiceResponse getByIdInvoice = new GetInvoiceResponse();
        getByIdInvoice.setInvoiceDate(getByIdInvoiceDto.getInvoiceDate());
        getByIdInvoice.setDueDate(getByIdInvoiceDto.getDueDate());
        getByIdInvoice.setTotalAmount(getByIdInvoiceDto.getTotalAmount());
        getByIdInvoice.setCurrency(getByIdInvoiceDto.getCurrency());
        getByIdInvoice.setStatus(getByIdInvoiceDto.getStatus());

        return getByIdInvoice;
    }
    @PostMapping
    public void add(@RequestBody AddInvoiceRequest addInvoiceDto){
        Invoice addInvoice = new Invoice();

        Order order = orderRepository.findById(addInvoiceDto.getOrderId()).orElseThrow();
        addInvoice.setOrder(order);
        Customer customer = customerRepository.findById(addInvoiceDto.getCustomerId()).orElseThrow();
        addInvoice.setCustomer(customer);
        addInvoice.setInvoiceDate(addInvoiceDto.getInvoiceDate());
        addInvoice.setDueDate(addInvoiceDto.getDueDate());
        addInvoice.setTotalAmount(addInvoiceDto.getTotalAmount());
        addInvoice.setCurrency(addInvoiceDto.getCurrency());
        addInvoice.setStatus(addInvoiceDto.getStatus());
        invoiceRepository.save(addInvoice);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateInvoiceRequest updateInvoiceDto){
        Invoice updateInvoice = invoiceRepository.findById(id).orElseThrow();
        Order order = orderRepository.findById(updateInvoiceDto.getOrderId()).orElseThrow();
        updateInvoice.setOrder(order);
        Customer customer = customerRepository.findById(updateInvoiceDto.getCustomerId()).orElseThrow();
        updateInvoice.setCustomer(customer);
        updateInvoice.setInvoiceDate(updateInvoiceDto.getInvoiceDate());
        updateInvoice.setDueDate(updateInvoiceDto.getDueDate());
        updateInvoice.setTotalAmount(updateInvoiceDto.getTotalAmount());
        updateInvoice.setCurrency(updateInvoiceDto.getCurrency());
        updateInvoice.setStatus(updateInvoiceDto.getStatus());
        invoiceRepository.save(updateInvoice);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Invoice deleteInvoice = invoiceRepository.findById(id).orElseThrow();
        invoiceRepository.delete(deleteInvoice);
    }
}
