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
    @CacheEvict(value = "category", allEntries = true)
    public boolean createCategory(CreateCategoryRequest createCategoryRequest) {
        String categoryName = createCategoryRequest.getCategoryName();
        assertCategoryDoesNotExist(categoryName);
        Category category = mapRequestToCategory(createCategoryRequest);
        saveCategory(category);
        return true;
    }

    private void assertCategoryDoesNotExist(String categoryName) {
        Optional<Category> existingCategory = categoryRepository.findByCategoryName(categoryName);
        if (existingCategory.isPresent()) {
            throw new AuthException(EErrorType.CATEGORY_HAS_BEEN);
        }
    }

    private Category mapRequestToCategory(CreateCategoryRequest createCategoryRequest) {
        return ICategoryMapper.INSTANCE.toCategory(createCategoryRequest);
    }

    private Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Cacheable(value = "category")
    public List<CategoryResponse> getAllCategory() {
        List<Category> categories = fetchAllCategories();
        return convertToCategoryResponse(categories);
    }

    private List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }

    private List<CategoryResponse> convertToCategoryResponse(List<Category> categories) {
        return categories.stream().map(ICategoryMapper.INSTANCE::toCategoryResponse).collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict(value = "category", allEntries = true)
    public boolean deleteCategory(Long categoryId) {
        Category category = findCategoryById(categoryId);
        deleteCategory(category);
        return true;
    }

    private Category findCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new AuthException(EErrorType.CATEGORY_NOT_FOUND));
    }

    private void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }
    public Optional<Category> getCategoryFromCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
