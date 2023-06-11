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


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    public OrderService(OrderRepository orderRepository, OrderItemService orderItemService, ProductService productService) {
        this.orderRepository = orderRepository;

        this.orderItemService = orderItemService;
        this.productService = productService;
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
        productService.changeStockWithOrder(createOrderRequest.getItems());
        orderItemService.createOrderItems(order, createOrderRequest.getItems());
        return true;
    }


    public List<OrdersResponse> getOrdersForUser(Integer id) {
        return createOrderResponseList(orderRepository.findByUserId(id));
    }

    public List<OrdersResponse> getAllOrders() {
        return createOrderResponseList(orderRepository.findAll());
    }

    private List<OrdersResponse> createOrderResponseList(List<Order> orders) {
        List<OrdersResponse> ordersResponses = new ArrayList<>();
        orders.forEach(x -> {
            ordersResponses.add(createOrderResponse(x));
        });
        return ordersResponses;
    }

    private OrdersResponse createOrderResponse(Order x) {
        return OrdersResponse.builder()
                .address(x.getAddress())
                .eTrackingStatus(x.getETrackingStatus())
                .amount(x.getAmount())
                .phone(x.getPhone())
                .build();
    }

    @Transactional
    public OrdersResponse updateTrackingStatus(Long id, OrderTrackingStatusRequest orderTrackingStatusRequest) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order with id " + id + " not found"));
        order.setETrackingStatus(orderTrackingStatusRequest.getETrackingStatus());
        orderRepository.save(order);
        return createOrderResponse(order);
    }
}
