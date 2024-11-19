package com.tfm.tfm.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.AutonomousCommunityDto;
import com.tfm.tfm.entity.AutonomousCommunityEntity;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.LocationEntity;
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

	public ProvinceEntity getProvinceEntity(String name){
		Optional<ProvinceEntity> provinceEntity = provinceRepository.findByName(name);

		//This should not happend
		if(provinceEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Province dos not exist");

		return provinceEntity.get();
	}

	public List<BrandEntity> getBrandsByProvince(String province){
		Optional<ProvinceEntity> provinceEntity = provinceRepository.findByName(province);

		if(provinceEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No brands");
		
		List<LocationEntity> locations = provinceEntity.get().getLocations();

		Set<BrandEntity> brands = new HashSet<>();

    for (LocationEntity location : locations) {
      brands.addAll(location.getBrands());
    }

    return brands.stream().collect(Collectors.toList());
	}

}
