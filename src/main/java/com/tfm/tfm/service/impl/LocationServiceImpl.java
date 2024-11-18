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
import com.tfm.tfm.response.PositionStackResponse;
import com.tfm.tfm.response.PositionStackResponse.Data;
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
		
		LocationEntity locationEntity = getNewLocationEntity(locationDto);
		
		locationRepository.save(locationEntity);
		
		return getLocationResponse(locationEntity);
		
	}
	
	private LocationEntity getNewLocationEntity(LocationDto locationDto) {
		
		if(locationRepository.existsByName(locationDto.getName())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location already exists");
		
        Data data = consultPositionStack(locationDto.getName());

        ProvinceEntity provinceEntity = provinceService.getProvinceEntity(data.getRegion());
				AutonomousCommunityEntity autonomousCommunityEntity = autonomousCommunityService.getAutonomousCommunityEntity(data.getRegion_code());

				LocationEntity locationEntity = new LocationEntity(generalService.capitalizeFirstLetter(locationDto.getName()));
				
				provinceEntity.addLocation(locationEntity);
				provinceEntity.setAutonomousCommunity(autonomousCommunityEntity);

				locationEntity.setProvince(provinceEntity);
				
				provinceRepository.save(provinceEntity);
				locationRepository.save(locationEntity);
        
		return locationEntity;
	}
	
	private Data consultPositionStack(String location) {
        String url = "https://api.positionstack.com/v1/forward?access_key=a3ba8afb1483f954fd32a732b4d3101e&query=" + location + "&country=ES";
        PositionStackResponse response = restTemplate.getForObject(url, PositionStackResponse.class);
        
        if (response == null || response.getData() == null || response.getData().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no data for the location.");
        return response.getData().get(0);
	}

	private LocationEntity getLocationEntity(LocationDto locationDto) {
		
		Optional <LocationEntity> locationEntity = locationRepository.findByName(locationDto.getName());

		if(locationEntity.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location does not exist");
		
		return locationEntity.get();
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

	public	void deleteLocation(LocationDto locationDto){
	
		Optional<LocationEntity> location = locationRepository.findByName(locationDto.getName());
		
		if(location.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location does not exist");
		
		locationRepository.delete(location.get());
	}

}
