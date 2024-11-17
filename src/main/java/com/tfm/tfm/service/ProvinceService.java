package com.tfm.tfm.service;

import com.tfm.tfm.entity.ProvinceEntity;

public interface ProvinceService {
		
	ProvinceEntity createProvince(String name);

	ProvinceEntity getProvinceEntity(String name);
}
