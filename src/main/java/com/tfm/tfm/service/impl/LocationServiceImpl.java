package com.tfm.tfm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.LocationDto;
import com.tfm.tfm.entity.AutonomousCommunityEntity;
import com.tfm.tfm.entity.LocationEntity;
import com.tfm.tfm.entity.ProvinceEntity;
import com.tfm.tfm.repository.LocationRepository;
import com.tfm.tfm.repository.ProvinceRepository;
import com.tfm.tfm.response.LocationResponse;
import com.tfm.tfm.service.AutonomousCommunityService;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.LocationService;
import com.tfm.tfm.service.ProvinceService;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired private LocationRepository locationRepository;
	@Autowired private ProvinceRepository provinceRepository;
	@Autowired private AutonomousCommunityService autonomousCommunityService;

	@Autowired private GeneralService generalService;
	@Autowired private ProvinceService provinceService;
	
  private RestTemplate restTemplate;

	public LocationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
  }

	public LocationResponse createLocation(LocationDto locationDto) {
		
		LocationEntity locationEntity = getLocationEntity(locationDto);
				
		return getLocationResponse(locationEntity);
	}

	private LocationEntity getLocationEntity(LocationDto locationDto) {
		
		Optional <LocationEntity> locationEntity = locationRepository.findByName(locationDto.getName());

		if(locationEntity.isEmpty()) return getNewLocationEntity(locationDto);
		
		return locationEntity.get();
	}

	private LocationEntity getNewLocationEntity(LocationDto locationDto) {
				
		ProvinceEntity provinceEntity = provinceService.getProvinceEntity(locationDto.getProvince());
				
		LocationEntity locationEntity = new LocationEntity(locationDto.getName(), provinceEntity);
				
		provinceEntity.addLocation(locationEntity);
				
		provinceRepository.save(provinceEntity);
		locationRepository.save(locationEntity);
        
		return locationEntity;
	}
	
	private LocationResponse getLocationResponse(LocationEntity locationEntity) {
		return new LocationResponse(locationEntity.getName(), locationEntity.getProvince().getName(), locationEntity.getProvince().getAutonomousCommunity().getName());
	}

	public List<LocationEntity> getListLocationEntity(List<String> locations) {
		List<LocationEntity> validLocations = new ArrayList<>();

		locations.forEach(location -> {
			var locationEntity = locationRepository.findByName(location);
			if(locationEntity.isPresent()) 
				validLocations.add(locationEntity.get());
		});
		
		return validLocations;
	}

}
