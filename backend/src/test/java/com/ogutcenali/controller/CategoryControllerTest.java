package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateCategoryRequest;
import com.ogutcenali.dto.response.CategoryResponse;
import com.ogutcenali.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        CategoryController categoryController = new CategoryController(categoryService);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testCreateCategory() throws Exception {
        when(categoryService.createCategory(any(CreateCategoryRequest.class)))
                .thenReturn(true);

        mockMvc.perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryName\":\"test\"}")) // Use an example request payload
                .andExpect(status().isOk());

        verify(categoryService, times(1)).createCategory(any(CreateCategoryRequest.class));
    }

    @Test
    public void testGetAllCategories() throws Exception {
        mockMvc.perform(get("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).getAllCategory();
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Long categoryId = 1L;
        when(categoryService.deleteCategory(categoryId)).thenReturn(true);

                mockMvc.perform(delete("/api/v1/categories/" + categoryId)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(categoryService, times(1)).deleteCategory(categoryId);
    }


}
