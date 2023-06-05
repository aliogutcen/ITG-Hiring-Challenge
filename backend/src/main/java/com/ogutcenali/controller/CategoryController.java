package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateCategoryRequest;
import com.ogutcenali.dto.response.CategoryResponse;
import com.ogutcenali.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        return ResponseEntity.ok(categoryService.createCategory(createCategoryRequest));
    }
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
