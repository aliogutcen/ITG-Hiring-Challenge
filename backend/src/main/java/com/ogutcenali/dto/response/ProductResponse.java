package com.ogutcenali.dto.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponse  implements Serializable {
    private Long id;
    private String productName;

    private Integer stock;

    private String image01;
    private String image02;
    private String image03;

    private Double price;
    private String desc;
    private String categoryName;
    private Long categoryId;

}
