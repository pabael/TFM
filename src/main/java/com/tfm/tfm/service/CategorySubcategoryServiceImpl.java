package com.tfm.tfm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public void deleteCategory(CategoryDto categoryDto){
		
		Optional<CategoryEntity> category = categoryRepository.findByName(categoryDto.getName());
		
		if(category.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "category does not exist");
		
		categoryRepository.delete(category.get());
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

	public void deleteSubcategory(SubcategoryDto subcategoryDto){
		
		Optional<SubcategoryEntity> subcategory = subcategoryRepository.findByName(subcategoryDto.getName());
		
		if(subcategory.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "subcategory does not exist");
		
		subcategoryRepository.delete(subcategory.get());
	}

	//Assign subcategory to a category

	public CategoryResponse assignSubcategoryToCategory(CategorySubcategoryDto catSubDto) {
		
		var categoryEntity = categoryRepository.findByName(catSubDto.getCategory());
		var subcategoryEntity = subcategoryRepository.findByName(catSubDto.getSubcategory());

		if(categoryEntity.isEmpty() || subcategoryEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subcategory does not exist");

		if(categoryRepository.existsByNameAndSubcategories_Name(catSubDto.getCategory(), catSubDto.getSubcategory())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category and subcategory relationship already exists");

		categoryEntity.get().addSubcategory(subcategoryEntity.get());
		categoryRepository.save(categoryEntity.get());
		
		return getCategoryResponse(categoryEntity.get());
	}

	public void deleteRelationship(CategorySubcategoryDto catSubDto){
		var categoryEntity = categoryRepository.findByName(catSubDto.getCategory());
		var subcategoryEntity = subcategoryRepository.findByName(catSubDto.getSubcategory());
	
		if(categoryEntity.isEmpty() || subcategoryEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category or subcategory does not exist");

		if(!categoryRepository.existsByNameAndSubcategories_Name(catSubDto.getCategory(), catSubDto.getSubcategory())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category and subcategory relationship does not exist");
		
		categoryEntity.get().deleteSubcategory(subcategoryEntity.get());
		categoryRepository.save(categoryEntity.get());
		subcategoryRepository.save(subcategoryEntity.get());
	}

}
