package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateOrderRequest;
import com.ogutcenali.dto.request.OrderTrackingStatusRequest;
import com.ogutcenali.dto.response.OrdersResponse;
import com.ogutcenali.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrderTest() {
        CreateOrderRequest request = new CreateOrderRequest();
        // ... initialize request as needed


        ResponseEntity<?> response = orderController.createOrder(request);

        assertEquals(200, response.getStatusCodeValue());
        verify(orderService, times(1)).createOrder(any(CreateOrderRequest.class));
    }

    @Test
    public void getAllOrdersTest() {
        when(orderService.getAllOrders()).thenReturn(Arrays.asList(new OrdersResponse(), new OrdersResponse()));

        ResponseEntity<List<OrdersResponse>> response = orderController.getAllOrders();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void updateTrackingStatusTest() {
        Long id = 1L;
        OrderTrackingStatusRequest request = new OrderTrackingStatusRequest();
        // ... initialize request as needed
        when(orderService.updateTrackingStatus(eq(id), any(OrderTrackingStatusRequest.class))).thenReturn(new OrdersResponse());

        ResponseEntity<OrdersResponse> response = orderController.updateTrackingStatus(id, request);

        assertEquals(200, response.getStatusCodeValue());
        verify(orderService, times(1)).updateTrackingStatus(eq(id), any(OrderTrackingStatusRequest.class));
    }

}
