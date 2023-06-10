package com.ogutcenali.service;

import com.ogutcenali.dto.request.CreateOrderRequest;
import com.ogutcenali.dto.request.OrderTrackingStatusRequest;
import com.ogutcenali.dto.response.OrdersResponse;
import com.ogutcenali.model.Order;
import com.ogutcenali.model.enums.ETrackingStatus;
import com.ogutcenali.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;

        this.orderItemService = orderItemService;
    }
    @Transactional
    public Boolean createOrder(CreateOrderRequest createOrderRequest) {
        Order order = Order.builder()
                .userId(createOrderRequest.getUserId())
                .amount(createOrderRequest.getAmount())
                .address(createOrderRequest.getAddress())
                .eTrackingStatus(ETrackingStatus.CREATED)
                .amount(createOrderRequest.getAmount())
                .phone(createOrderRequest.getPhone())
                .build();

        orderRepository.save(order);
        orderItemService.createOrderItems(order, createOrderRequest.getItems());
        return true;
    }


    public List<OrdersResponse> getOrdersForUser(Integer id) {
        List<OrdersResponse> ordersResponses = new ArrayList<>();

        orderRepository.findByUserId(id).forEach(x -> {
            ordersResponses.add(OrdersResponse.builder()
                    .address(x.getAddress())
                    .eTrackingStatus(x.getETrackingStatus())
                    .amount(x.getAmount())
                    .phone(x.getPhone())
                    .build());
        });
        return ordersResponses;
    }

    public List<OrdersResponse> getAllOrders() {
        List<OrdersResponse> ordersResponses = new ArrayList<>();

        orderRepository.findAll().forEach(x -> {
            ordersResponses.add(OrdersResponse.builder()
                    .address(x.getAddress())
                    .eTrackingStatus(x.getETrackingStatus())
                    .amount(x.getAmount())
                    .phone(x.getPhone())
                    .build());
        });
        return ordersResponses;
    }

    @Transactional
    public OrdersResponse updateTrackingStatus(Long id, OrderTrackingStatusRequest orderTrackingStatusRequest) {
        Optional<Order> order = orderRepository.findById(id);
        order.get().setETrackingStatus(orderTrackingStatusRequest.getETrackingStatus());
        orderRepository.save(order.get());
        return OrdersResponse.builder()
                .phone(order.get().getPhone())
                .eTrackingStatus(order.get().getETrackingStatus())
                .address(order.get().getAddress())
                .amount(order.get().getAmount())
                .build();

    }
}
