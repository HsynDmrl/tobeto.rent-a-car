package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.services.abstracts.OrderService;
import com.rentacar.rentaacar.services.dtos.requests.Order.AddOrderRequest;
import com.rentacar.rentaacar.services.dtos.requests.Order.UpdateOrderRequest;
import com.rentacar.rentaacar.services.dtos.responses.Order.GetOrderListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Order.GetOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    @GetMapping
    public List<GetOrderListResponse> getAll() {
        return this.orderService.getAll();
    }
    @GetMapping("{id}")
    public GetOrderResponse getById(@PathVariable int id){
        return this.orderService.getById(id);
    }
    @PostMapping
    public void add(@RequestBody AddOrderRequest addOrderDto){
        orderService.add(addOrderDto);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateOrderRequest updateOrderDto){
        orderService.update(id, updateOrderDto);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id, String areYouSure)
    {
        orderService.delete(id,areYouSure);
    }
}
