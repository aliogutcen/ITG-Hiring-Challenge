package com.ogutcenali.dto.request;

import com.ogutcenali.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private Integer userId;

    private String address;

    private String phone;
    private Double amount;

    private List<OrderItem> items;

}
