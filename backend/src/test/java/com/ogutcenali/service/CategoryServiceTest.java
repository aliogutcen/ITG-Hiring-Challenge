package com.ogutcenali.service;

import com.ogutcenali.dto.request.CreateCategoryRequest;
import com.ogutcenali.model.Category;
import com.ogutcenali.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void testCreateCategory() {
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setCategoryName("Test");
        when(categoryRepository.findByCategoryName(anyString())).thenReturn(Optional.empty());
        assertTrue(categoryService.createCategory(request));
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    public void testGetAllCategory() {
        Category category = new Category();
        category.setCategoryName("Test");
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        assertFalse(categoryService.getAllCategory().isEmpty());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteCategory() {
        Long id = 1L;
        Category category = new Category();
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        assertTrue(categoryService.deleteCategory(id));
        verify(categoryRepository, times(1)).delete(category);
    }
}
