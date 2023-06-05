package com.ogutcenali.dto.response;

import com.ogutcenali.model.Product;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryResponse implements Serializable {
    private Long categoryId;
    private String categoryName;

}
