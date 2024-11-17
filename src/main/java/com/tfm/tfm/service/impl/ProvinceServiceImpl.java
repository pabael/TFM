package com.tfm.tfm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.entity.ProvinceEntity;
import com.tfm.tfm.repository.ProvinceRepository;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService{

	@Autowired private ProvinceRepository provinceRepository;

	@Autowired private GeneralService generalService;

	public ProvinceEntity createProvince(String name) {
		
		if(provinceRepository.existsByName(name)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Province already exists");

		return create(name);		
	}

	public ProvinceEntity getProvinceEntity(String name) {
		
		Optional <ProvinceEntity> provinceEntity = provinceRepository.findByName(name);

		if(provinceEntity.isPresent()) return provinceEntity.get();
		
		return create(name);
	}
	
	private ProvinceEntity create(String name) {
		
		ProvinceEntity provinceEntity = new ProvinceEntity(generalService.capitalizeFirstLetter(name));
		
		provinceRepository.save(provinceEntity);
		
		return provinceEntity;
	}
}
