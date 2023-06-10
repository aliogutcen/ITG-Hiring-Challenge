package com.ogutcenali.dto.request;

import com.ogutcenali.model.Category;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateProductRequest {

    private String productName;

    private Integer stock;

    private Double price;
    private MultipartFile image01;
    private MultipartFile image02;
    private MultipartFile image03;

    private String desc;

    private Long categoryId;
}
