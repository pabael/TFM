package com.tfm.tfm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tfm.tfm.dto.LocationDto;
import com.tfm.tfm.entity.LocationEntity;
import com.tfm.tfm.repository.LocationRepository;
import com.tfm.tfm.response.LocationResponse;
import com.tfm.tfm.service.GeneralService;
import com.tfm.tfm.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{

	@Autowired private LocationRepository locationRepository;

	@Autowired private GeneralService generalService;

	public LocationResponse createLocation(LocationDto locationDto) {
		
		LocationEntity locationEntity = getNewLocationEntity(locationDto);
		
		locationRepository.save(locationEntity);
		
		return getLocationResponse(locationEntity);
		
	}
	
	private LocationEntity getNewLocationEntity(LocationDto locationDto) {
		
		if(locationRepository.existsByName(locationDto.getName())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location already exists");
		
		return new LocationEntity(generalService.capitalizeFirstLetter(locationDto.getName()));
	}

	private LocationEntity getLocationEntity(LocationDto locationDto) {
		
		Optional <LocationEntity> locationEntity = locationRepository.findByName(locationDto.getName());

		if(locationEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location does not exist");
		
		return locationEntity.get();
	}
	
	private LocationResponse getLocationResponse(LocationEntity locationEntity) {
		return new LocationResponse(locationEntity.getName());
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

	public	void deleteLocation(LocationDto locationDto){
	
		Optional<LocationEntity> location = locationRepository.findByName(locationDto.getName());
		
		if(location.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location does not exist");
		
		locationRepository.delete(location.get());
	}

}
