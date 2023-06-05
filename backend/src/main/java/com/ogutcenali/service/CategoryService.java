package com.ogutcenali.service;

import com.ogutcenali.dto.request.CreateCategoryRequest;
import com.ogutcenali.dto.response.CategoryResponse;
import com.ogutcenali.exception.AuthException;
import com.ogutcenali.exception.EErrorType;
import com.ogutcenali.mapper.ICategoryMapper;
import com.ogutcenali.model.Category;
import com.ogutcenali.repository.CategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @CacheEvict(value = "category",allEntries = true)
    public Object createCategory(CreateCategoryRequest createCategoryRequest) {
        Optional<Category> category = categoryRepository.findByCategoryName(createCategoryRequest.getCategoryName());
        if (category.isPresent()) throw new AuthException(EErrorType.CATEGORY_HAS_BEEN);
        categoryRepository.save(ICategoryMapper.INSTANCE.toCategory(createCategoryRequest));
        return true;
    }

    @Cacheable(value = "category")
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(x -> CategoryResponse.builder()
                        .categoryName(x.getCategoryName())
                        .categoryId(x.getId())
                        .build())
                .collect(Collectors.toList());
    }
    @Transactional
    @CacheEvict(value = "category",allEntries = true)
    public Boolean deleteCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) throw new AuthException(EErrorType.CATEGORY_NOT_FOUND);
        categoryRepository.delete(category.get());
        return true;
    }

    public Optional<Category> getCategoryFromCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
