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

    private String image;

    private String desc;


}
