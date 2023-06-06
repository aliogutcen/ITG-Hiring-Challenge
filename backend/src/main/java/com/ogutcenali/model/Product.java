package com.ogutcenali.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private Integer stock;

    private String image;

    private String describe;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Integer quantity;
    @ManyToOne
    private Order order;
}