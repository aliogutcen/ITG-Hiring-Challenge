package com.ogutcenali.dto.request;

import com.ogutcenali.model.enums.ETrackingStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderTrackingStatusRequest {

    ETrackingStatus eTrackingStatus;
}
