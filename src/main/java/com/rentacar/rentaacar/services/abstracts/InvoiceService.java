package com.rentacar.rentaacar.services.abstracts;

import com.rentacar.rentaacar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Invoice.UpdateInvoiceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Invoice.GetInvoiceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Invoice.GetInvoiceResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InvoiceService {
    public List<GetInvoiceListResponse> getAll();
    public GetInvoiceResponse getById(@PathVariable int id);
    public void add(@RequestBody AddInvoiceRequest addInvoiceDto);
    public void update(@PathVariable int id, @RequestBody UpdateInvoiceRequest updateInvoiceDto);
    public void delete(@PathVariable int id, String areYouSure);
}
