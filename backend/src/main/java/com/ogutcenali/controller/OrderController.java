package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateOrderRequest;
import com.ogutcenali.dto.request.OrderTrackingStatusRequest;
import com.ogutcenali.dto.response.OrdersResponse;
import com.ogutcenali.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return ResponseEntity.ok(orderService.createOrder(createOrderRequest));
    }
    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrdersResponse>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/tracking-status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrdersResponse> updateTrackingStatus(@PathVariable Long id ,@RequestBody OrderTrackingStatusRequest orderTrackingStatusRequest){
        return ResponseEntity.ok(orderService.updateTrackingStatus(id,orderTrackingStatusRequest));
    }


}
