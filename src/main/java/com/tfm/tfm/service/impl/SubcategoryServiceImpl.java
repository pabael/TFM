package com.tfm.tfm.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.SubcategoryDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.CategoryEntity;
import com.tfm.tfm.entity.SubcategoryEntity;
import com.tfm.tfm.repository.CategoryRepository;
import com.tfm.tfm.repository.SubcategoryRepository;
import com.tfm.tfm.response.SubcategoryResponse;
import com.tfm.tfm.service.CategoryService;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.SubcategoryService;

@Service
public class SubcategoryServiceImpl implements SubcategoryService{

	@Autowired private SubcategoryRepository subcategoryRepository;
	@Autowired private CategoryRepository categoryRepository;
	
	@Autowired private GeneralService generalService;
	@Autowired private CategoryService categoryService;
	
	public SubcategoryResponse createSubcategory(SubcategoryDto subcategoryDto) {

		if(subcategoryRepository.existsByNameAndCategory_Name(subcategoryDto.getName(), subcategoryDto.getCategory())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subcategory already exists");

		CategoryEntity categoryEntity = categoryService.getCategoryEntity(subcategoryDto.getCategory());

		SubcategoryEntity subcategoryEntity = new SubcategoryEntity(generalService.capitalizeFirstLetter(subcategoryDto.getName()), categoryEntity);

		subcategoryRepository.save(subcategoryEntity);
		
		return getSubcategoryResponse(subcategoryEntity);
		
	}
	
	private SubcategoryResponse getSubcategoryResponse(SubcategoryEntity subcategoryEntity) {
		
		String category = subcategoryEntity.getCategory().getName();

		return new SubcategoryResponse(subcategoryEntity.getName(), category);
	}
	
	public List<SubcategoryEntity> getListSubcategoryEntity(List<Map<String, String>> categoriesAndSubcategories) {
		
		if(categoriesAndSubcategories == null) return null;

		return categoriesAndSubcategories.stream()
		    .map(map -> subcategoryRepository.findByNameAndCategory_Name(map.get("subcategory"), map.get("category")))
		    .filter(Optional::isPresent)
		    .map(Optional::get)
		    .collect(Collectors.toList());
	}

	public void deleteSubcategory(SubcategoryDto subcategoryDto){
		
		Optional<SubcategoryEntity> subcategory = subcategoryRepository.findByNameAndCategory_Name(subcategoryDto.getName(), subcategoryDto.getCategory());
		
		if(subcategory.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subcategory does not exist");
		
		CategoryEntity categoryEntity = categoryService.getCategoryEntity(subcategoryDto.getCategory());
		categoryEntity.deleteSubcategory(subcategory.get());
		categoryRepository.save(categoryEntity);

		subcategoryRepository.delete(subcategory.get());
	}

	public List<BrandEntity> getBrandsBySubcategory(String subcategory, String category){
		
		Optional<SubcategoryEntity> subcategoryEntity = subcategoryRepository.findByNameAndCategory_Name(subcategory, category);
		if(subcategoryEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subcategory does not exist");

		return subcategoryEntity.get().getBrands();
	}
}
