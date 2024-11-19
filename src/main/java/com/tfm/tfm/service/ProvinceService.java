package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.AutonomousCommunityDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.ProvinceEntity;
import com.tfm.tfm.response.ProvinceResponse;

public interface ProvinceService {
		
	List<ProvinceResponse> getProvinceList(AutonomousCommunityDto autonomousCommunityDto);

	ProvinceEntity getProvinceEntity(String name);

	List<BrandEntity> getBrandsByProvince(String province);
	List<BrandEntity> getBrandsByProvince(ProvinceEntity provinceEntity);

}