package com.tfm.tfm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfm.dto.AutonomousCommunityDto;
import com.tfm.tfm.entity.AutonomousCommunityEntity;
import com.tfm.tfm.entity.ProvinceEntity;
import com.tfm.tfm.repository.ProvinceRepository;
import com.tfm.tfm.response.ProvinceResponse;
import com.tfm.tfm.service.AutonomousCommunityService;
import com.tfm.tfm.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService{

	@Autowired private ProvinceRepository provinceRepository;

	@Autowired private AutonomousCommunityService autonomousCommunityService;

	public List<ProvinceResponse> getProvinceList(AutonomousCommunityDto autonomousCommunityDto){
	
		List<ProvinceEntity> listDto = getProvinceEntityList(autonomousCommunityDto);

		return listDto.stream()
			.map(entity -> new ProvinceResponse(entity.getName()))
			.collect(Collectors.toList()); 

	}

	public List<ProvinceEntity> getProvinceEntityList(AutonomousCommunityDto autonomousCommunityDto){
	
		AutonomousCommunityEntity autonomousCommunityEntity = autonomousCommunityService.getAutonomousCommunityEntity(autonomousCommunityDto.getName());

		return provinceRepository.findByAutonomousCommunity(autonomousCommunityEntity);
	}

}
