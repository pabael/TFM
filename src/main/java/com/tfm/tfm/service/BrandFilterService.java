package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.response.BrandResponse;

public interface BrandFilterService {
		
	List<BrandResponse> getBrandsByCategory(String category);

	List<BrandResponse> getBrandsByLabel(String label);
	
	List<BrandResponse> getBrandsByConsumer(String consumer);

	List<BrandResponse> getBrandsByPrice(Integer price);

	List<BrandResponse> getBrandsByLocation(String location);

}
