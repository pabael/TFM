package com.tfm.tfm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tfm.dto.CategoryDto;
import com.tfm.tfm.dto.CategorySubcategoryDto;
import com.tfm.tfm.dto.SubcategoryDto;
import com.tfm.tfm.response.CategoryResponse;
import com.tfm.tfm.response.SubcategoryResponse;
import com.tfm.tfm.service.CategorySubcategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class CategorySubcategoryController {
	@Autowired private CategorySubcategoryService categorySubcategoryService;

	@PostMapping("/category")
	@Operation(summary = "Create new Category", 
    description = "Create a new category in the system.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public CategoryResponse create(@RequestBody @Valid CategoryDto categoryDto) {
		return categorySubcategoryService.createCategory(categoryDto);
	}
	
	@PostMapping("/subcategory")
	@Operation(summary = "Create new Subcategory", 
    description = "Create a new subcategory in the system.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public SubcategoryResponse create(@RequestBody @Valid SubcategoryDto subcategoryDto) {
		return categorySubcategoryService.createSubcategory(subcategoryDto);
	}
	
	@PostMapping("/assign")
	@Operation(summary = "Assign subcategories to a category")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public CategoryResponse assign(@RequestBody @Valid CategorySubcategoryDto catSubDto) {
		return categorySubcategoryService.assignSubcategoryToCategory(catSubDto);
	}
}
