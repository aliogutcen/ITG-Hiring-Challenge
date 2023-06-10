package com.ogutcenali.dto.response;

import com.ogutcenali.model.enums.ETrackingStatus;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersResponse implements Serializable {

    private Double amount;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private ETrackingStatus eTrackingStatus;
}
