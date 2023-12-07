package com.rentacar.rentaacar.services.abstracts;

import com.rentacar.rentaacar.services.dtos.requests.Order.AddOrderRequest;
import com.rentacar.rentaacar.services.dtos.requests.Order.UpdateOrderRequest;
import com.rentacar.rentaacar.services.dtos.responses.Order.GetOrderListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Order.GetOrderResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {
    public List<GetOrderListResponse> getAll();
    public GetOrderResponse getById(@PathVariable int id);
    public void add(AddOrderRequest addOrderDto);
    public void update(@PathVariable int id, @RequestBody UpdateOrderRequest updateOrderDto);
    public void delete(@PathVariable int id, String areYouSure);
}
