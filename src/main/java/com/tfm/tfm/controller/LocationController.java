package com.tfm.tfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tfm.dto.LocationDto;
import com.tfm.tfm.response.LocationResponse;
import com.tfm.tfm.service.LocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class LocationController {
	@Autowired private LocationService locationService;

	@PostMapping("/location")
	@Operation(summary = "Create new location", 
    description = "Create a new location in the system.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public LocationResponse create(@RequestBody @Valid LocationDto locationDto) {
		return locationService.createLocation(locationDto);
	}
}
