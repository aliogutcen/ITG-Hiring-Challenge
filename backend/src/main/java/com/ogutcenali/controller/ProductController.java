package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateProductRequest;
import com.ogutcenali.dto.request.UpdateStockRequest;
import com.ogutcenali.dto.response.ProductResponse;
import com.ogutcenali.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    @ResponseStatus ( HttpStatus . CREATED )
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest){
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
    @ResponseStatus ( HttpStatus. OK )
    public ResponseEntity<ProductResponse> updateStockProduct(@PathVariable Long id,@RequestBody UpdateStockRequest updateStockRequest){
        return ResponseEntity.ok(productService.updateStock(id,updateStockRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteProduct (@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

}
