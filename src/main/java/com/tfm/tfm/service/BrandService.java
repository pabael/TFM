package com.tfm.tfm.service;

import com.tfm.tfm.dto.BrandDto;
import com.tfm.tfm.response.BrandResponse;

public interface BrandService {
		
	BrandResponse createBrand(BrandDto brandDto);
}
