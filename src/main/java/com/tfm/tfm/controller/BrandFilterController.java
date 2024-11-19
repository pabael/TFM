package com.tfm.tfm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.response.BrandResponse;
import com.tfm.tfm.service.BrandFilterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class BrandFilterController {
	@Autowired private BrandFilterService brandFilterService;

	@GetMapping("/brands/category")
	@Operation(summary = "Get all brands from a category")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public List<BrandResponse> getByCategory(@RequestParam @Valid String category) {
		return brandFilterService.getBrandsByCategory(category);
	}

	@GetMapping("/brands/label")
	@Operation(summary = "Get all brands from label")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public List<BrandResponse> getByLabel(@RequestParam @Valid String label) {
		return brandFilterService.getBrandsByLabel(label);
	}

	@GetMapping("/brands/consumer")
	@Operation(summary = "Get all brands from consumer")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public List<BrandResponse> getByConsumer(@RequestParam @Valid String consumer) {
		return brandFilterService.getBrandsByConsumer(consumer);
	}

	@GetMapping("/brands/price")
	@Operation(summary = "Get all brands from price")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public List<BrandResponse> getByPrice(@RequestParam @Valid Integer price) {
		return brandFilterService.getBrandsByPrice(price);
	}

	@GetMapping("/brands/location")
	@Operation(summary = "Get all brands from location")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public List<BrandResponse> getByLocation(@RequestParam @Valid String location) {
		return brandFilterService.getBrandsByLocation(location);
	}

	@GetMapping("/brands/province")
	@Operation(summary = "Get all brands from province")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public List<BrandResponse> getByProvince(@RequestParam @Valid String province) {
		return brandFilterService.getBrandsByProvince(province);
	}
}
