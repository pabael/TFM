package com.tfm.tfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.dto.BrandDto;
import com.tfm.tfm.dto.SubcategoryDto;
import com.tfm.tfm.response.CategoryResponse;
import com.tfm.tfm.response.BrandResponse;
import com.tfm.tfm.response.SubcategoryResponse;
import com.tfm.tfm.service.CategorySubcategoryService;
import com.tfm.tfm.service.BrandService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class BrandController {
	@Autowired private BrandService brandService;

	@PostMapping("/brand")
	@Operation(summary = "Create new Brand", 
    description = "Create a new brand in the system.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public BrandResponse create(@RequestBody @Valid BrandDto brandDto) {
		return brandService.createBrand(brandDto);
	}
}
