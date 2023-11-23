package com.rentacar.rentaacar.controllers;

import com.rentacar.rentaacar.dtos.requests.Order.AddOrderRequest;
import com.rentacar.rentaacar.dtos.requests.Order.UpdateOrderRequest;
import com.rentacar.rentaacar.dtos.responses.Order.GetOrderListResponse;
import com.rentacar.rentaacar.dtos.responses.Order.GetOrderResponse;
import com.rentacar.rentaacar.entities.Customer;
import com.rentacar.rentaacar.entities.Employee;
import com.rentacar.rentaacar.entities.Order;
import com.rentacar.rentaacar.entities.Vehicle;
import com.rentacar.rentaacar.repositories.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrdersController {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final EmployeeRepository employeeRepository;

    public OrdersController(OrderRepository orderRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository, EmployeeRepository employeeRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
        this.employeeRepository = employeeRepository;
    }
    @GetMapping
    public List<GetOrderListResponse> getAll() {
        List<Order> orderList = orderRepository.findAll();
        List<GetOrderListResponse> getOrderDtoList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);

            GetOrderListResponse dto = new GetOrderListResponse();
            dto.setPickUpDate(order.getPickUpDate());
            dto.setDropDate(order.getDropDate());
            dto.setDelivered(order.isDelivered());
            dto.setNotes(order.getNotes());
            dto.setTotalCost(order.getTotalCost());

            getOrderDtoList.add(dto);
        }
        return getOrderDtoList;
    }


    @GetMapping("{id}")
    public GetOrderResponse getById(@PathVariable int id){
        Order getByIdOrderDto = orderRepository.findById(id).orElseThrow();
        GetOrderResponse getByIdOrder = new GetOrderResponse();

        getByIdOrder.setPickUpDate(getByIdOrderDto.getPickUpDate());
        getByIdOrder.setDropDate(getByIdOrderDto.getDropDate());
        getByIdOrder.setDelivered(getByIdOrderDto.isDelivered());
        getByIdOrder.setNotes(getByIdOrderDto.getNotes());
        getByIdOrder.setTotalCost(getByIdOrderDto.getTotalCost());

        return getByIdOrder;
    }
    @PostMapping
    public void add(@RequestBody AddOrderRequest addOrderDto){
        Order addOrder = new Order();

        Customer customer = customerRepository.findById(addOrderDto.getCustomerId()).orElseThrow();
        addOrder.setCustomer(customer);
        addOrder.setPickUpDate(addOrderDto.getPickUpDate());
        addOrder.setDropDate(addOrderDto.getDropDate());
        Vehicle vehicle = vehicleRepository.findById(addOrderDto.getVehicleId()).orElseThrow();
        addOrder.setVehicle(vehicle);
        addOrder.setDelivered(addOrderDto.isDelivered());
        addOrder.setNotes(addOrderDto.getNotes());
        addOrder.setTotalCost(addOrderDto.getTotalCost());
        Employee employee = employeeRepository.findById(addOrderDto.getEmployeeId()).orElseThrow();
        addOrder.setEmployee(employee);
        orderRepository.save(addOrder);
    }
    @PutMapping("{id}")
    public void update(@PathVariable int id, @RequestBody UpdateOrderRequest updateOrderDto){
        Order updateOrder = orderRepository.findById(id).orElseThrow();

        Customer customer = customerRepository.findById(updateOrderDto.getCustomerId()).orElseThrow();
        updateOrder.setCustomer(customer);
        updateOrder.setPickUpDate(updateOrderDto.getPickUpDate());
        updateOrder.setDropDate(updateOrderDto.getDropDate());
        Vehicle vehicle = vehicleRepository.findById(updateOrderDto.getVehicleId()).orElseThrow();
        updateOrder.setVehicle(vehicle);
        updateOrder.setDelivered(updateOrderDto.isDelivered());
        updateOrder.setNotes(updateOrderDto.getNotes());
        updateOrder.setTotalCost(updateOrderDto.getTotalCost());
        Employee employee = employeeRepository.findById(updateOrderDto.getVehicleId()).orElseThrow();
        updateOrder.setEmployee(employee);
        orderRepository.save(updateOrder);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable int id)
    {
        Order deleteOrder = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(deleteOrder);
    }
}
