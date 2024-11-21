package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.CategoryEntity;
import com.tfm.tfm.response.CategoryResponse;

public interface CategoryService {
		
	CategoryResponse createCategory(CategoryDto categoryDto);
	void deleteCategory(String category);
	List<CategoryResponse> getAllCategories();

	CategoryEntity getCategoryEntity(String category);

	List<CategoryEntity> getListCategoryEntity(List<CategoryDto> categories);
	List<CategoryResponse> getListCategoryResponse(List<CategoryEntity> categories);

	List<BrandEntity> getBrandsByCategory(String category);
}
