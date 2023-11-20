package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.entities.Order;
import com.rentacar.rentaacar.repositories.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrdersController {
    private final OrderRepository orderRepository;
    public OrdersController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @GetMapping
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    @GetMapping("{id}")
    public Order getById(@PathVariable int id){
        return orderRepository.findById(id).orElseThrow();
    }
    @PostMapping
    public void add(@RequestBody Order order){
        orderRepository.save(order);
    }
    @PutMapping
    public void update(@RequestBody Order order){
        orderRepository.findById(order.getId()).orElseThrow();
        orderRepository.save(order);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Order orderToDelete = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(orderToDelete);
    }
}
