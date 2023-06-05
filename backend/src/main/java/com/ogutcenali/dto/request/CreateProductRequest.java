package com.ogutcenali.dto.request;

import com.ogutcenali.model.Category;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateProductRequest {

    private String productName;

    private Integer stock;

    private String image;

    private String desc;

    private Long categoryId;
}
