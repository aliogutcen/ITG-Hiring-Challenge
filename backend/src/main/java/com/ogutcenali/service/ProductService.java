package com.ogutcenali.service;


import com.ogutcenali.dto.request.CreateProductRequest;
import com.ogutcenali.dto.request.UpdateStockRequest;
import com.ogutcenali.dto.response.ProductResponse;
import com.ogutcenali.model.Category;
import com.ogutcenali.model.OrderItem;
import com.ogutcenali.model.Product;
import com.ogutcenali.repository.ProductRepository;
import com.ogutcenali.utility.ImageUpload;
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

    private final ImageUpload imageUpload;
    public ProductService(ProductRepository productRepository, CategoryService categoryService, ImageUpload imageUpload) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.imageUpload = imageUpload;
    }

    //Added ımage cdn
    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public Object createProduct(CreateProductRequest createProductRequest) {
        Optional<Category> category = categoryService.getCategoryFromCategoryId(createProductRequest.getCategoryId());
        Product product = Product.builder()
                .productName(createProductRequest.getProductName())
                .category(category.get())
                .describe(createProductRequest.getDesc())
                .stock(createProductRequest.getStock())
                .image01(imageUpload.imageUpload(createProductRequest.getImage01()))
                .image02(imageUpload.imageUpload(createProductRequest.getImage02()))
                .image03(imageUpload.imageUpload(createProductRequest.getImage03()))
                .price(createProductRequest.getPrice())
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
                        .image01(x.getImage01())
                        .image02(x.getImage02())
                        .image03(x.getImage03())
                        .price(x.getPrice())
                        .categoryId(x.getCategory().getId())
                        .categoryName(x.getCategory().getCategoryName())
                        .build())
                .collect(Collectors.toList());
    }

    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        Optional<Category> category = categoryService.getCategoryFromCategoryId(product.get().getCategory().getId());

        return ProductResponse.builder()
                .productName(product.get().getProductName())
                .id(product.get().getId())
                .stock(product.get().getStock())
                .desc(product.get().getDescribe())
                .image01(product.get().getImage01())
                .image02(product.get().getImage02())
                .image03(product.get().getImage03())
                .price(product.get().getPrice())
                .categoryName(category.get().getCategoryName())
                .categoryId(category.get().getId())
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
        Optional<Category> category = categoryService.getCategoryFromCategoryId(product.get().getCategory().getId());
        return ProductResponse.builder()
                .productName(product.get().getProductName())
                .id(product.get().getId())
                .stock(product.get().getStock())
                .desc(product.get().getDescribe())
                .image01(product.get().getImage01())
                .image02(product.get().getImage02())
                .image03(product.get().getImage03())
                .price(product.get().getPrice())
                .categoryName(category.get().getCategoryName())
                .categoryId(category.get().getId())
                .build();

    }

    public void changeStockWithOrder(List<OrderItem> items) {
        items.forEach(x -> {
            Optional<Product> product = productRepository.findById(x.getId());
            product.get().setStock(product.get().getStock() - x.getQuantity());
            productRepository.save(product.get());

        });


    }
}
