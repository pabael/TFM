package com.tfm.tfm.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.LocationDto;
import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.LocationEntity;
import com.tfm.tfm.entity.ProvinceEntity;
import com.tfm.tfm.repository.LocationRepository;
import com.tfm.tfm.repository.ProvinceRepository;
import com.tfm.tfm.response.LocationResponse;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.LocationService;
import com.tfm.tfm.service.ProvinceService;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired private LocationRepository locationRepository;
	@Autowired private ProvinceRepository provinceRepository;

	@Autowired private ProvinceService provinceService;
	@Autowired private GeneralService generalService;

	public LocationResponse createLocation(LocationDto locationDto) {
		
		if(locationRepository.findByName(locationDto.getName()).isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location already exists");

		LocationEntity locationEntity = getNewLocationEntity(locationDto);
				
		return  new LocationResponse(generalService.capitalizeFirstLetter(locationEntity.getName()), 
																locationEntity.getProvince().getName(), 
																locationEntity.getProvince().getAutonomousCommunity().getName());
	}


	private LocationEntity getNewLocationEntity(LocationDto locationDto) {
				
		ProvinceEntity provinceEntity = provinceService.getProvinceEntity(locationDto.getProvince());
				
		LocationEntity locationEntity = new LocationEntity(locationDto.getName(), provinceEntity);
				
		provinceEntity.addLocation(locationEntity);
				
		provinceRepository.save(provinceEntity);
		locationRepository.save(locationEntity);
        
		return locationEntity;
	}

	public List<LocationEntity> getListLocationEntity(List<String> locations) {

		return locations.stream()
    .map(location -> locationRepository.findByName(location))
    .filter(Optional::isPresent)
    .map(Optional::get)
    .collect(Collectors.toList());
	}

	public List<BrandEntity> getBrandsByLocation(String location){
		
		Optional<LocationEntity> locationEntity = locationRepository.findByName(location);

		if(locationEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location does not exist");

		return locationEntity.get().getBrands();
	}

}
