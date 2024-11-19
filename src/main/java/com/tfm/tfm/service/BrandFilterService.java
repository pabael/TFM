package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.response.BrandResponse;

public interface BrandFilterService {
		
	List<BrandResponse> getBrandsByCategory(String category);

	List<BrandResponse> getBrandsByLabel(String label);

}
