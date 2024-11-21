package com.tfm.tfm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.BrandDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.CategoryEntity;
import com.tfm.tfm.entity.ConsumerEntity;
import com.tfm.tfm.entity.LabelEntity;
import com.tfm.tfm.entity.LocationEntity;
import com.tfm.tfm.entity.SubcategoryEntity;
import com.tfm.tfm.repository.BrandRepository;
import com.tfm.tfm.response.BrandResponse;
import com.tfm.tfm.service.BrandService;
import com.tfm.tfm.service.CategoryService;
import com.tfm.tfm.service.ConsumerService;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.LabelService;
import com.tfm.tfm.service.LocationService;
import com.tfm.tfm.service.PriceService;
import com.tfm.tfm.service.SubcategoryService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired private BrandRepository brandRepository;
	
	@Autowired private GeneralService generalService;
	@Autowired private CategoryService categoryService;
	@Autowired private SubcategoryService subcategoryService;
	@Autowired private LabelService labelService;
	@Autowired private ConsumerService consumerService;
	@Autowired private PriceService priceService;
	@Autowired private LocationService locationService;

	public BrandResponse createBrand(BrandDto brandDto) {
		
		BrandEntity brandEntity = getNewBrandEntity(brandDto);
		
		brandRepository.save(brandEntity);
		
		return getBrandResponse(brandEntity);
		
	}
	
	private BrandEntity getNewBrandEntity(BrandDto brandDto) {
		
		if(brandRepository.findByName(brandDto.getName()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand already exists");

		return new BrandEntity(
				generalService.capitalizeFirstLetter(brandDto.getName()), 
				brandDto.getSummary(), 
				brandDto.getUrl(),
				brandDto.getMaterials(), 
				brandDto.isCrueltyFree(),
				brandDto.isVegan(),
				brandDto.getCommitment(),
				brandDto.getProduction(),
				categoryService.getListCategoryEntity(getCategories(brandDto.getCategoriesAndSubcategories())),
				subcategoryService.getListSubcategoryEntity(brandDto.getCategoriesAndSubcategories()),
				labelService.getListLabelEntity(brandDto.getLabels()),
				consumerService.getListConsumerEntity(brandDto.getConsumers()),
				priceService.getPriceEntity(brandDto.getPrice()),
				locationService.getListLocationEntity(brandDto.getLocations())
			);
	}

	private List<String> getCategories (List<Map<String, String>> categoriesAndSubcategories){
		
		if(categoriesAndSubcategories == null) return null;

		return categoriesAndSubcategories.stream()
    	.map(map -> map.get("category"))
    	.collect(Collectors.toList());
	}

	private List<Map<String, String>> getCategoriesAndSubcategoriesMap(BrandEntity brandEntity){

		List<CategoryEntity> categories = brandEntity.getCategories();
		if(categories == null) return null;
		
		List<SubcategoryEntity> subcategories = brandEntity.getSubcategories();

		List<Map<String, String>> categoryAndSubcategory = new ArrayList<>();

		if(subcategories != null){
			Set<CategoryEntity> categoriesWithSubcategories = subcategories.stream()
				.map(subcategory -> {
						Map<String, String> map = new HashMap<>();
						map.put("category", subcategory.getCategory().getName());
						map.put("subcategory", subcategory.getName());
						categoryAndSubcategory.add(map);
						return subcategory.getCategory();
				})
				.collect(Collectors.toSet());

			categories = categories.stream()
        .filter(category -> !categoriesWithSubcategories.contains(category))
				.collect(Collectors.toList());
		}

		categories.forEach(category -> {
				Map<String, String> map = new HashMap<>();
				map.put("category", category.getName());
				map.put("subcategory", null);
				categoryAndSubcategory.add(map);
		});

		return categoryAndSubcategory;
	}
	
	public BrandResponse getBrandResponse(BrandEntity brandEntity) {
		return new BrandResponse(
				brandEntity.getName(), 
				brandEntity.getSummary(),
				brandEntity.getUrl(),
				brandEntity.getMaterials(),
				brandEntity.isCrueltyFree(),
				brandEntity.isVegan(),
				brandEntity.getCommitment(),
				brandEntity.getProduction(),
				getCategoriesAndSubcategoriesMap(brandEntity),
				brandEntity.getLabels() == null ? null : brandEntity.getLabels().stream().map(LabelEntity::getName).collect(Collectors.toList()),
				brandEntity.getConsumers() == null ? null : brandEntity.getConsumers().stream().map(ConsumerEntity::getType).collect(Collectors.toList()),
				brandEntity.getPrice().getPriceRange(),
				brandEntity.getLocations() == null ? null : brandEntity.getLocations().stream().map(LocationEntity::getName).collect(Collectors.toList())
		);
	}

	public	BrandResponse updateBrand(BrandDto brandDto){
	
	
		Optional<BrandEntity> brand = brandRepository.findByName(brandDto.getName());
		if(brand.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand does not exist");

		if(brandDto.getName() != null) brand.get().setName(generalService.capitalizeFirstLetter(brandDto.getName()));
		if(brandDto.getSummary() != null) brand.get().setSummary(brandDto.getSummary());
		if(brandDto.getUrl() != null) brand.get().setUrl(brandDto.getUrl());
		if(brandDto.getMaterials() != null) brand.get().setMaterials(brandDto.getMaterials());
		if(brandDto.isCrueltyFree() != null) brand.get().setCrueltyFree(brandDto.isCrueltyFree());
		if(brandDto.isVegan() != null) brand.get().setVegan(brandDto.isVegan());
		if(brandDto.getCommitment() != null) brand.get().setCommitment(brandDto.getCommitment());
		if(brandDto.getProduction() != null) brand.get().setProduction(brandDto.getProduction());
		
		if(brandDto.getCategoriesAndSubcategories() != null) {
			brand.get().setCategories(categoryService.getListCategoryEntity(getCategories(brandDto.getCategoriesAndSubcategories())));
			brand.get().setSubcategories(subcategoryService.getListSubcategoryEntity(brandDto.getCategoriesAndSubcategories()));
		}
		if(brandDto.getLabels() != null) brand.get().setLabels(labelService.getListLabelEntity(brandDto.getLabels()));
		if(brandDto.getConsumers() != null) brand.get().setConsumers(consumerService.getListConsumerEntity(brandDto.getConsumers()));
		
		if(brandDto.getPrice() != null) brand.get().setPrice(priceService.getPriceEntity(brandDto.getPrice()));

		if(brandDto.getLocations() != null) brand.get().setLocations(locationService.getListLocationEntity(brandDto.getLocations()));

		brandRepository.save(brand.get());
		
		return getBrandResponse(brand.get());
	}

	public	void deleteBrand(String brandName){
	
		Optional<BrandEntity> brand = brandRepository.findByName(brandName);
		
		if(brand.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand does not exist");
		
		brandRepository.delete(brand.get());
	}

	public	List<BrandEntity> getBrandListIsVegan(boolean isVegan){

		return brandRepository.findByVegan(isVegan);
	}

	public	List<BrandEntity> getBrandListIsCrueltyFree(boolean isCrueltyFree){

		return brandRepository.findByCrueltyFree(isCrueltyFree);
	}
}
