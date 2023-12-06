package com.rentacar.rentaacar.services.concretes;

import com.rentacar.rentaacar.entities.*;
import com.rentacar.rentaacar.repositories.*;
import com.rentacar.rentaacar.services.abstracts.OrderService;
import com.rentacar.rentaacar.services.dtos.requests.Order.AddOrderRequest;
import com.rentacar.rentaacar.services.dtos.requests.Order.UpdateOrderRequest;
import com.rentacar.rentaacar.services.dtos.responses.Order.GetOrderListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Order.GetOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderManager implements OrderService {
    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public List<GetOrderListResponse> getAll() {
        List<Order> orderList = orderRepository.findAll();
        List<GetOrderListResponse> getOrderDtoList = new ArrayList<>();

        if (orderList.isEmpty())
            throw new RuntimeException("Kayıtlı şipariş bulunmuyor.");

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
    @Override
    public GetOrderResponse getById(int id) {
        Order getByIdOrderDto = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir fatura bulunamadı."));

        GetOrderResponse getByIdOrder = new GetOrderResponse();
        getByIdOrder.setPickUpDate(getByIdOrderDto.getPickUpDate());
        getByIdOrder.setDropDate(getByIdOrderDto.getDropDate());
        getByIdOrder.setDelivered(getByIdOrderDto.isDelivered());
        getByIdOrder.setNotes(getByIdOrderDto.getNotes());
        getByIdOrder.setTotalCost(getByIdOrderDto.getTotalCost());
        return getByIdOrder;
    }
    @Override
    public void add(AddOrderRequest addOrderDto) {

        if (addOrderDto.getPickUpDate().isAfter(addOrderDto.getDropDate()))
            throw new RuntimeException("Teslim tarihi, Alım tarihinden önce olamaz.");

        if (addOrderDto.getTotalCost() <= 0)
            throw new RuntimeException("Toplam tutar geçersiz.");

        Order addOrder = new Order();

        Customer customer = customerRepository.findById(addOrderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir müşteri bulunamadı."));
        addOrder.setCustomer(customer);

        addOrder.setPickUpDate(addOrderDto.getPickUpDate());
        addOrder.setDropDate(addOrderDto.getDropDate());

        Vehicle vehicle = vehicleRepository.findById(addOrderDto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir araba bulunamadı."));
        addOrder.setVehicle(vehicle);


        Invoice invoice = invoiceRepository.findById(addOrderDto.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));
        addOrder.setInvoice(invoice);
        addOrder.setDelivered(addOrderDto.isDelivered());
        addOrder.setNotes(addOrderDto.getNotes());
        addOrder.setTotalCost(addOrderDto.getTotalCost());

        Employee employee = employeeRepository.findById(addOrderDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir çalışan bulunamadı."));
        addOrder.setEmployee(employee);

        orderRepository.save(addOrder);
    }
    @Override
    public void update(int id, UpdateOrderRequest updateOrderDto) {

        Order updateOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));

        if (updateOrderDto.getPickUpDate().isAfter(updateOrderDto.getDropDate()))
            throw new RuntimeException("Güncellenen Teslim tarihi, Alım tarihinden önce olamaz.");

        if (updateOrderDto.getTotalCost() <= 0)
            throw new RuntimeException("Güncellenen Toplam tutar geçersiz.");

        Customer customer = customerRepository.findById(updateOrderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir müşteri bulunamadı."));
        updateOrder.setCustomer(customer);

        updateOrder.setPickUpDate(updateOrderDto.getPickUpDate());
        updateOrder.setDropDate(updateOrderDto.getDropDate());

        Vehicle vehicle = vehicleRepository.findById(updateOrderDto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir araba bulunamadı."));
        updateOrder.setVehicle(vehicle);


        Invoice invoice = invoiceRepository.findById(updateOrderDto.getInvoiceId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir sipariş bulunamadı."));
        updateOrder.setInvoice(invoice);
        updateOrder.setDelivered(updateOrderDto.isDelivered());
        updateOrder.setNotes(updateOrderDto.getNotes());
        updateOrder.setTotalCost(updateOrderDto.getTotalCost());

        Employee employee = employeeRepository.findById(updateOrderDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Bu ID ile kayıtlı bir çalışan bulunamadı."));
        updateOrder.setEmployee(employee);

        orderRepository.save(updateOrder);
    }
    @Override
    public void delete(int id, String areYouSure) {
        Order deleteOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silmek istediğiniz sipariş bulunamıyor."));

        deleteOrder.setDeleteConfirmation(areYouSure);

        if (deleteOrder.isDeleteConfirmed()) {
            orderRepository.delete(deleteOrder);
        } else {
            throw new RuntimeException("Silme işlemi iptal edildi. Onaylamak için areYouSure bölümüne 'evet' yazmanız gerekiyor.");
        }
    }
}
