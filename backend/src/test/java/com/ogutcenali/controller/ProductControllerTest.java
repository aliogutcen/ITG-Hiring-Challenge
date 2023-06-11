package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateProductRequest;
import com.ogutcenali.dto.request.UpdateStockRequest;
import com.ogutcenali.dto.response.ProductResponse;
import com.ogutcenali.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createProductTest() {
        CreateProductRequest request = new CreateProductRequest();
        // ... initialize request as needed
        when(productService.createProduct(any(CreateProductRequest.class))).thenReturn(new ProductResponse());

        ResponseEntity<?> response = productController.createProduct(request);

        assertEquals(200, response.getStatusCodeValue());
        verify(productService, times(1)).createProduct(any(CreateProductRequest.class));
    }

    @Test
    public void getAllProductsTest() {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(new ProductResponse(), new ProductResponse()));

        ResponseEntity<List<ProductResponse>> response = productController.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void getProductTest() {
        Long id = 1L;
        when(productService.getProduct(id)).thenReturn(new ProductResponse());

        ResponseEntity<ProductResponse> response = productController.getProduct(id);

        assertEquals(200, response.getStatusCodeValue());
        verify(productService, times(1)).getProduct(id);
    }

    @Test
    public void updateStockProductTest() {
        Long id = 1L;
        UpdateStockRequest request = new UpdateStockRequest();
        // ... initialize request as needed
        when(productService.updateStock(eq(id), any(UpdateStockRequest.class))).thenReturn(new ProductResponse());

        ResponseEntity<ProductResponse> response = productController.updateStockProduct(id, request);

        assertEquals(200, response.getStatusCodeValue());
        verify(productService, times(1)).updateStock(eq(id), any(UpdateStockRequest.class));
    }

    @Test
    public void deleteProductTest() {
        Long id = 1L;
        when(productService.deleteProduct(id)).thenReturn(true);

        ResponseEntity<?> response = productController.deleteProduct(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(true, response.getBody());
        verify(productService, times(1)).deleteProduct(id);
    }
}
