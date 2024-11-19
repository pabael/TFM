package com.tfm.tfm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.dto.CategorySubcategoryDto;
import com.tfm.tfm.dto.SubcategoryDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.CategoryEntity;
import com.tfm.tfm.entity.SubcategoryEntity;
import com.tfm.tfm.repository.CategoryRepository;
import com.tfm.tfm.repository.SubcategoryRepository;
import com.tfm.tfm.response.CategoryResponse;
import com.tfm.tfm.service.CategoryService;
import com.tfm.tfm.service.GeneralService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired private CategoryRepository categoryRepository;
	@Autowired private SubcategoryRepository subcategoryRepository;
	
	@Autowired private GeneralService generalService;

	//Categories
	
	public CategoryResponse createCategory(CategoryDto categoryDto) {
		
		if(categoryRepository.findByName(categoryDto.getName()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists");

		CategoryEntity categoryEntity = new CategoryEntity(generalService.capitalizeFirstLetter(categoryDto.getName()));
		
		categoryRepository.save(categoryEntity);
		
		return getCategoryResponse(categoryEntity);
		
	}
	
	private CategoryResponse getCategoryResponse(CategoryEntity categoryEntity) {
	
		List<String> subcategories = categoryEntity.getSubcategories().stream().map(SubcategoryEntity::getName).collect(Collectors.toList());
	
		return new CategoryResponse(categoryEntity.getName(), subcategories);
	}
	
	public CategoryEntity getCategoryEntity(String category) {
		
		Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(category);
		if(categoryEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category does not exist");

		return categoryEntity.get();
	}

	public List<BrandEntity> getBrandsByCategory(String category){
		
		CategoryEntity categoryEntity = getCategoryEntity(category);
		
		return categoryEntity.getBrands();
	}
	
	public List<CategoryEntity> getListCategoryEntity(List<String> categories) {
		
		return categories.stream()
    .map(category -> categoryRepository.findByName(category))
    .filter(Optional::isPresent)
    .map(Optional::get)
    .collect(Collectors.toList());
	}

	public void deleteCategory(CategoryDto categoryDto){
		
		Optional<CategoryEntity> category = categoryRepository.findByName(categoryDto.getName());
		
		if(category.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category does not exist");
		
		categoryRepository.delete(category.get());
	}
}
