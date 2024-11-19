package com.tfm.tfm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.PriceEntity;
import com.tfm.tfm.repository.PriceRepository;
import com.tfm.tfm.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService{

	@Autowired private PriceRepository priceRepository;
	
	public PriceEntity getPriceEntity(Integer price) {
		
		Optional<PriceEntity> priceEntity = priceRepository.findByPriceRange(price);

		if(priceEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price does not exist");

		return priceEntity.get();
	}

	public	List<BrandEntity> getBrandsByPrice(Integer price){
		Optional<PriceEntity> priceEntity = priceRepository.findByPriceRange(price);

		if(priceEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price does not exist");

		return priceEntity.get().getBrands();
	}

}
