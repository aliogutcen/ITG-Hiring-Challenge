package com.ogutcenali.service;

import com.ogutcenali.model.Order;
import com.ogutcenali.model.OrderItem;
import com.ogutcenali.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void createOrderItems(Order order, List<OrderItem> orderItemList) {
        orderItemList.forEach(x -> {
            OrderItem orderItem = OrderItem.builder()
                    .orderId(order.getId())
                    .productId(x.getId())
                    .quantity(x.getQuantity())
                    .totalPrice(x.getTotalPrice())
                    .build();
            orderItemRepository.save(orderItem);
        });
    }
}
