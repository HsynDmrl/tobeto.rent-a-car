package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.abstracts.InvoiceService;
import com.rentacar.rentaacar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Invoice.GetInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/invoices")
@AllArgsConstructor
public class InvoicesController {
    private final InvoiceService invoiceService;
    @GetMapping
    public List<GetInvoiceListResponse> getAll() {
        return invoiceService.getAll();
    }
    @GetMapping("{id}")
    public GetInvoiceResponse getById(@PathVariable int id){
        return invoiceService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddInvoiceRequest addInvoiceDto){
        invoiceService.add(addInvoiceDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateInvoiceRequest updateInvoiceDto){
        invoiceService.update(id, updateInvoiceDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        invoiceService.delete(id, areYouSure);
    }
}
