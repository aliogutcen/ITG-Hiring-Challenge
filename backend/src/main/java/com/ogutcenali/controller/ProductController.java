package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateProductRequest;
import com.ogutcenali.dto.request.UpdateStockRequest;
import com.ogutcenali.dto.response.ProductResponse;
import com.ogutcenali.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createProduct( CreateProductRequest createProductRequest){
        return ResponseEntity.ok(productService.createProduct(createProductRequest));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @PutMapping("/{id}/stock-update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductResponse> updateStockProduct(@PathVariable Long id,@RequestBody UpdateStockRequest updateStockRequest){
        return ResponseEntity.ok(productService.updateStock(id,updateStockRequest));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?>deleteProduct (@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
