package com.tfm.tfm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import io.swagger.v3.oas.annotations.media.Schema;
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

	@DeleteMapping("/category")
	@Operation(summary = "Delete Category information", 
    description = "Delete category giving Name.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public void deleteCategory(@RequestBody @Valid CategoryDto categoryDto) {
		categorySubcategoryService.deleteCategory(categoryDto);
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

	@DeleteMapping("/subcategory")
	@Operation(summary = "Delete subcategory information", 
    description = "Delete subcategory giving Name.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public void deleteSuybcategory(@RequestBody @Valid SubcategoryDto subcategoryDto) {
		categorySubcategoryService.deleteSubcategory(subcategoryDto);
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

@DeleteMapping("/relation")
	@Operation(summary = "Delete relation", 
    description = "Delete rtelation between a category and a subcategory giving the names.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public void deleteRelationship(@RequestBody @Valid CategorySubcategoryDto catSubDto) {
		categorySubcategoryService.deleteRelationship(catSubDto);
	}
}
