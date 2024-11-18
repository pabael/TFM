package com.tfm.tfm.service.impl;

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
import com.tfm.tfm.service.BrandService;
import com.tfm.tfm.service.CategorySubcategoryService;
import com.tfm.tfm.service.ConsumerService;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.LabelService;
import com.tfm.tfm.service.LocationService;
import com.tfm.tfm.service.PriceService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired private BrandRepository brandRepository;
	
	@Autowired private GeneralService generalService;
	@Autowired private CategorySubcategoryService categorySubcategoryService;
	@Autowired private LabelService labelService;
	@Autowired private ConsumerService consumerService;
	@Autowired private PriceService priceService;
	@Autowired private LocationService locationService;

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
				categorySubcategoryService.getListSubcategoryEntity(brandDto.getSubcategories()),
				labelService.getListLabelEntity(brandDto.getLabels()),
				consumerService.getListConsumerEntity(brandDto.getConsumers()),
				priceService.getPriceEntity(brandDto.getPrice()),
				locationService.getListLocationEntity(brandDto.getLocations())
			);
	}
	
	public BrandResponse getBrandResponse(BrandEntity brandEntity) {
		List<String> categories = new ArrayList<>();
		brandEntity.getCategories().forEach(category -> {
			categories.add(category.getName());
		});
		
		List<String> subcategories = new ArrayList<>();
		brandEntity.getSubcategories().forEach(subcategory -> {
			subcategories.add(subcategory.getName());
		});

		List<String> labels = new ArrayList<>();
		brandEntity.getLabels().forEach(label -> {
			labels.add(label.getName());
		});

		List<String> consumers = new ArrayList<>();
		brandEntity.getConsumers().forEach(consumer -> {
			consumers.add(consumer.getType());
		});

		List<String> locations = new ArrayList<>();
		brandEntity.getLocations().forEach(location -> {
			locations.add(location.getName());
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
				subcategories,
				labels,
				consumers,
				brandEntity.getPrice().getPriceRange(),
				locations
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
		
		if(brandDto.getCategories() != null) brand.get().setCategories(categorySubcategoryService.getListCategoryEntity(brandDto.getCategories()));
		if(brandDto.getSubcategories() != null) brand.get().setSubcategories(categorySubcategoryService.getListSubcategoryEntity(brandDto.getSubcategories()));
		if(brandDto.getLabels() != null) brand.get().setLabels(labelService.getListLabelEntity(brandDto.getLabels()));
		if(brandDto.getConsumers() != null) brand.get().setConsumers(consumerService.getListConsumerEntity(brandDto.getConsumers()));
		
		if(brandDto.getPrice() != null) brand.get().setPrice(priceService.getPriceEntity(brandDto.getPrice()));

		if(brandDto.getLocations() != null) brand.get().setLocations(locationService.getListLocationEntity(brandDto.getLocations()));

		brandRepository.save(brand.get());
		
		return getBrandResponse(brand.get());
	}

	public	void deleteBrand(String brandName){
	
		Optional<BrandEntity> brand = brandRepository.findByName(brandName);
		
		if(brand.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "brand does not exist");
		
		brandRepository.delete(brand.get());
	}

}
