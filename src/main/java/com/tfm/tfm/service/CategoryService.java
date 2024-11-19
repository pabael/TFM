package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.dto.CategorySubcategoryDto;
import com.tfm.tfm.dto.SubcategoryDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.CategoryEntity;
import com.tfm.tfm.entity.SubcategoryEntity;
import com.tfm.tfm.response.CategoryResponse;
import com.tfm.tfm.response.SubcategoryResponse;

public interface CategoryService {
		
	CategoryResponse createCategory(CategoryDto categoryDto);
	void deleteCategory(CategoryDto categoryDto);

	CategoryEntity getCategoryEntity(String category);

	List<CategoryEntity> getListCategoryEntity(List<String> categories);

	List<BrandEntity> getBrandsByCategory(String category);
}
