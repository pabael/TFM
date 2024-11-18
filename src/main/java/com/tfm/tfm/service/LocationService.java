package com.tfm.tfm.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.tfm.tfm.dto.LocationDto;
import com.tfm.tfm.entity.LocationEntity;
import com.tfm.tfm.response.LocationResponse;

public interface LocationService {
			
	LocationResponse createLocation(LocationDto locationDto);

	List<LocationEntity> getListLocationEntity(List<String> locations);

}
