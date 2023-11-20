package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.entities.Invoice;
import com.rentacar.rentaacar.repositories.InvoiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
public class InvoicesController {
    private final InvoiceRepository invoiceRepository;
    public InvoicesController(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    @GetMapping
    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }
    @GetMapping("{id}")
    public Invoice getById(@PathVariable int id){
        return invoiceRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Invoice invoice){
        invoiceRepository.save(invoice);
    }
    @PutMapping
    public void update(@RequestBody Invoice invoice){
        invoiceRepository.findById(invoice.getId()).orElseThrow();
        invoiceRepository.save(invoice);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Invoice invoiceToDelete = invoiceRepository.findById(id).orElseThrow();
        invoiceRepository.delete(invoiceToDelete);
    }
}
