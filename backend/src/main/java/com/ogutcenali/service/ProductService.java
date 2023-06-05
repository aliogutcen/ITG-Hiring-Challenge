package com.ogutcenali.service;

import com.ogutcenali.dto.request.CreateProductRequest;
import com.ogutcenali.dto.request.UpdateStockRequest;
import com.ogutcenali.dto.response.ProductResponse;
import com.ogutcenali.model.Category;
import com.ogutcenali.model.Product;
import com.ogutcenali.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Object createProduct(CreateProductRequest createProductRequest) {
        Optional<Category> category = categoryService.getCategoryFromCategoryId(createProductRequest.getCategoryId());
        Product product = Product.builder()
                .productName(createProductRequest.getProductName())
                .category(category.get())
                .describe(createProductRequest.getDesc())
                .stock(createProductRequest.getStock())
                .build();
        productRepository.save(product);
        return true;
    }

    @Cacheable(value = "products")
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(x -> ProductResponse.builder()
                        .productName(x.getProductName())
                        .id(x.getId())
                        .stock(x.getStock())
                        .desc(x.getDescribe())
                        .image(x.getImage())
                        .build())
                .collect(Collectors.toList());
    }

    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return ProductResponse.builder()
                .productName(product.get().getProductName())
                .id(product.get().getId())
                .stock(product.get().getStock())
                .desc(product.get().getDescribe())
                .image(product.get().getImage())
                .build();
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Boolean deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        productRepository.delete(product.get());
        return true;
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public ProductResponse updateStock(Long id, UpdateStockRequest updateStockRequest) {
        Optional<Product> product = productRepository.findById(id);
        product.get().setStock(product.get().getStock() + updateStockRequest.getStock());
        productRepository.save(product.get());
        return ProductResponse.builder()
                .productName(product.get().getProductName())
                .id(product.get().getId())
                .stock(product.get().getStock())
                .desc(product.get().getDescribe())
                .image(product.get().getImage())
                .build();

    }
}
