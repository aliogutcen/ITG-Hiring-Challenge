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
@Table(name = "tbl_shipping")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ETrackingStatus trackingStatus;
    @OneToOne
    private Order order;
}
