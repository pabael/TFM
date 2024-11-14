package com.tfm.tfm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.dto.CategorySubcategoryDto;
import com.tfm.tfm.dto.SubcategoryDto;
import com.tfm.tfm.entity.CategoryEntity;
import com.tfm.tfm.entity.SubcategoryEntity;
import com.tfm.tfm.repository.CategoryRepository;
import com.tfm.tfm.repository.SubcategoryRepository;
import com.tfm.tfm.response.CategoryResponse;
import com.tfm.tfm.response.SubcategoryResponse;

@Service
public class CategorySubcategoryServiceImpl implements CategorySubcategoryService{

	@Autowired private CategoryRepository categoryRepository;
	@Autowired private SubcategoryRepository subcategoryRepository;
	
	@Autowired private GeneralService generalService;

	//Categories
	
	public CategoryResponse createCategory(CategoryDto categoryDto) {
		
		CategoryEntity categoryEntity = getCategoryEntity(categoryDto);
		
		categoryRepository.save(categoryEntity);
		
		return getCategoryResponse(categoryEntity);
		
	}
	
	private CategoryEntity getCategoryEntity(CategoryDto categoryDto) {
		
		if(!categoryRepository.findByName(categoryDto.getName()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category already exists");
		
		return new CategoryEntity(generalService.capitalizeFirstLetter(categoryDto.getName()));
	}
	
	private CategoryResponse getCategoryResponse(CategoryEntity categoryEntity) {
	
		List<String> subcategories = new ArrayList<>();
		categoryEntity.getSubcategories().forEach(subcategory -> {
			subcategories.add(subcategory.getName());
		});
		
		return new CategoryResponse(categoryEntity.getName(), subcategories);
	}
	
	public List<CategoryEntity> getListCategoryEntity(List<String> categories) {
		List<CategoryEntity> validCategories = new ArrayList<>();

		categories.forEach(category -> {
			var categoryEntity = categoryRepository.findByName(category);
			if(categoryEntity.isPresent()) 
				validCategories.add(categoryEntity.get());
		});
		
		return validCategories;
	}
	
	private List<CategoryResponse> getListCategoryResponse(List<CategoryEntity> categoryEntityList){
		
		List<CategoryResponse> categoryResponseList = new ArrayList<>();
		categoryEntityList.forEach(categoryEntity -> categoryResponseList.add(getCategoryResponse(categoryEntity)));
		return categoryResponseList;
	}
	
	//Subcategories
	
	public SubcategoryResponse createSubcategory(SubcategoryDto subcategoryDto) {
		
		SubcategoryEntity subcategoryEntity = getSubcategoryEntity(subcategoryDto);
		
		subcategoryRepository.save(subcategoryEntity);
		
		return getSubcategoryResponse(subcategoryEntity);
		
	}
	
	private SubcategoryEntity getSubcategoryEntity(SubcategoryDto subcategoryDto) {
		
		if(!subcategoryRepository.findByName(subcategoryDto.getName()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subcategory already exists");

		
		return new SubcategoryEntity(generalService.capitalizeFirstLetter(subcategoryDto.getName()));
	}
	
	private SubcategoryResponse getSubcategoryResponse(SubcategoryEntity subcategoryEntity) {
	
		List<String> categories = new ArrayList<>();
		subcategoryEntity.getCategories().forEach(category -> {
			categories.add(category.getName());
		});
		
		return new SubcategoryResponse(subcategoryEntity.getName(), categories);
	}
	
	public List<SubcategoryEntity> getListSubcategoryEntity(List<String> subcategories) {
		List<SubcategoryEntity> validSubcategories = new ArrayList<>();

		subcategories.forEach(subcategory -> {
			var subcategoryEntity = subcategoryRepository.findByName(subcategory);
			if(subcategoryEntity.isPresent()) 
				validSubcategories.add(subcategoryEntity.get());
		});
		
		return validSubcategories;
	}
	
	//Assign subcategories to categories
	public List<CategoryResponse> assignSubcategoriesToCategory(CategorySubcategoryDto catSubDto) {
		var categoryEntity = categoryRepository.findByName(catSubDto.getCategory());
				
		if(categoryEntity.isEmpty()){
			return new ArrayList<>();
		}

		List<SubcategoryEntity> validSubcategories = getListSubcategoryEntity(catSubDto.getSubcategories());

		validSubcategories.forEach(subcategory -> {
			if(!subcategoryRepository.existsByCategoriesNameAndName(categoryEntity.get().getName(), subcategory.getName())) {
				categoryEntity.get().addSubcategory(subcategory);
				categoryRepository.save(categoryEntity.get());
			}
		});
		
		List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
		
		return getListCategoryResponse(categoryEntityList);
	}
}
