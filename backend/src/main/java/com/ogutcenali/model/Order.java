package com.ogutcenali.model;

import com.ogutcenali.model.enums.ETrackingStatus;
import lombok.*;

import javax.persistence.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbl_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer userId;
    private Double amount;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private ETrackingStatus eTrackingStatus;


}
