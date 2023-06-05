package com.ogutcenali.mapper;

import com.ogutcenali.dto.request.CreateCategoryRequest;
import com.ogutcenali.dto.response.CategoryResponse;
import com.ogutcenali.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {

    ICategoryMapper INSTANCE  = Mappers.getMapper(ICategoryMapper.class);

    Category toCategory(final CreateCategoryRequest createCategoryRequest);

    CategoryResponse toCategoryResponse(final Category category);
}
