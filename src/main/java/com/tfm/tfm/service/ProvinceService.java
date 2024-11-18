package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.dto.AutonomousCommunityDto;
import com.tfm.tfm.response.ProvinceResponse;

public interface ProvinceService {
		
	List<ProvinceResponse> getProvinceList(AutonomousCommunityDto autonomousCommunityDto);

}
