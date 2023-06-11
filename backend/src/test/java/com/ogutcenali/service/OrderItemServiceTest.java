package com.ogutcenali.service;

import com.ogutcenali.model.Order;
import com.ogutcenali.model.OrderItem;
import com.ogutcenali.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderItemServiceTest {

    @MockBean
    private OrderItemRepository orderItemRepository;

    @Test
    public void createOrderItemsTest() {
        OrderItemService orderItemService = new OrderItemService(orderItemRepository);

        Order order = new Order();
        order.setId(1L);

        OrderItem item1 = new OrderItem();
        item1.setProductId(1L);
        item1.setQuantity(5);
        item1.setTotalPrice(100.0);

        OrderItem item2 = new OrderItem();
        item2.setProductId(2L);
        item2.setQuantity(3);
        item2.setTotalPrice(60.0);

        List<OrderItem> orderItemList = Arrays.asList(item1, item2);

        orderItemService.createOrderItems(order, orderItemList);

        Mockito.verify(orderItemRepository, Mockito.times(1)).saveAll(Mockito.anyList());
    }
}
