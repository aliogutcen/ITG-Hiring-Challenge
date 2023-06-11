package com.ogutcenali.service;

import com.ogutcenali.model.Order;
import com.ogutcenali.model.OrderItem;
import com.ogutcenali.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void createOrderItems(Order order, List<OrderItem> orderItemList) {
        List<OrderItem> orderItems = orderItemList.stream().map(x -> OrderItem.builder()
                .orderId(order.getId())
                .productId(x.getId())
                .quantity(x.getQuantity())
                .totalPrice(x.getTotalPrice())
                .build()).collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);
    }
}
