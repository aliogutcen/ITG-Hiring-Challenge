package com.ogutcenali.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


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
    @OneToMany(mappedBy = "order")
    List<Product> products;

}
