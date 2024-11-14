package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.dto.CategorySubcategoryDto;
import com.tfm.tfm.dto.SubcategoryDto;
import com.tfm.tfm.entity.CategoryEntity;
import com.tfm.tfm.entity.SubcategoryEntity;
import com.tfm.tfm.response.CategoryResponse;
import com.tfm.tfm.response.SubcategoryResponse;

public interface CategorySubcategoryService {
		
	CategoryResponse createCategory(CategoryDto categoryDto);
	SubcategoryResponse createSubcategory(SubcategoryDto subcategoryDto);

	public List<CategoryEntity> getListCategoryEntity(List<String> categories);
	public List<SubcategoryEntity> getListSubcategoryEntity(List<String> subcategories);

	public CategoryResponse assignSubcategoryToCategory(CategorySubcategoryDto catSubDto);

	
}
