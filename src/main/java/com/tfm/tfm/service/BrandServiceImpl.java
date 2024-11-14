package com.tfm.tfm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.BrandDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.repository.BrandRepository;
import com.tfm.tfm.response.BrandResponse;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired private BrandRepository brandRepository;

	@Autowired private CategorySubcategoryService categorySubcategoryService;
	
	@Autowired private GeneralService generalService;

	public BrandResponse createBrand(BrandDto brandDto) {
		
		BrandEntity brandEntity = getBrandEntity(brandDto);
		
		brandRepository.save(brandEntity);
		
		return getBrandResponse(brandEntity);
		
	}
	
	private BrandEntity getBrandEntity(BrandDto brandDto) {
		
		if(!brandRepository.findByName(brandDto.getName()).isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "brand already exists");
		
		return new BrandEntity(
				generalService.capitalizeFirstLetter(brandDto.getName()), 
				brandDto.getSummary(), 
				brandDto.getMaterials(), 
				brandDto.isCrueltyFree(),
				brandDto.isVegan(),
				brandDto.getCommitment(),
				brandDto.getProduction(),
				categorySubcategoryService.getListCategoryEntity(brandDto.getCategories()),
				categorySubcategoryService.getListSubcategoryEntity(brandDto.getSubcategories())
				);
	}
	
	private BrandResponse getBrandResponse(BrandEntity brandEntity) {
		List<String> categories = new ArrayList<>();
		brandEntity.getCategories().forEach(category -> {
			categories.add(category.getName());
		});
		
		List<String> subcategories = new ArrayList<>();
		brandEntity.getSubcategories().forEach(subcategory -> {
			subcategories.add(subcategory.getName());
		});
		
		return new BrandResponse(
				brandEntity.getName(), 
				brandEntity.getSummary(),
				brandEntity.getMaterials(),
				brandEntity.isCrueltyFree(),
				brandEntity.isVegan(),
				brandEntity.getCommitment(),
				brandEntity.getProduction(),
				categories,
				subcategories
		);
	}

	public	BrandResponse updateBrand(BrandDto brandDto){
	
		Optional<BrandEntity> brand = brandRepository.findByName(brandDto.getName());
		
		if(brand.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "brand does not exist");
		
		if(brandDto.getName() != null) brand.get().setName(generalService.capitalizeFirstLetter(brandDto.getName()));
		if(brandDto.getSummary() != null) brand.get().setSummary(brandDto.getSummary());
		if(brandDto.getMaterials() != null) brand.get().setMaterials(brandDto.getMaterials());
		if(brandDto.isCrueltyFree() != null) brand.get().setCrueltyFree(brandDto.isCrueltyFree());
		if(brandDto.isVegan() != null) brand.get().setVegan(brandDto.isVegan());
		if(brandDto.getCommitment() != null) brand.get().setCommitment(brandDto.getCommitment());
		if(brandDto.getProduction() != null) brand.get().setProduction(brandDto.getProduction());
		
		if(!brandDto.getCategories().isEmpty()) brand.get().setCategories(categorySubcategoryService.getListCategoryEntity(brandDto.getCategories()));
		if(brandDto.getSubcategories() != null) brand.get().setSubcategories(categorySubcategoryService.getListSubcategoryEntity(brandDto.getSubcategories()));

		brandRepository.save(brand.get());
		
		return getBrandResponse(brand.get());
	}

}
