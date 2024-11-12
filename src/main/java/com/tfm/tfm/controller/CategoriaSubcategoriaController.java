package com.tfm.tfm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tfm.dto.CategoriaDto;
import com.tfm.tfm.dto.CategoriaSubcategoriaDto;
import com.tfm.tfm.dto.SubcategoriaDto;
import com.tfm.tfm.response.CategoriaResponse;
import com.tfm.tfm.response.SubcategoriaResponse;
import com.tfm.tfm.service.CategoriaSubcategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class CategoriaSubcategoriaController {
	@Autowired private CategoriaSubcategoriaService categoriaSubcategoriaService;

	@PostMapping("/categoria")
	@Operation(summary = "Create new Categoria", 
    description = "Create a new category in the system.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public CategoriaResponse create(@RequestBody @Valid CategoriaDto categoriaDto) {
		return categoriaSubcategoriaService.createCategoria(categoriaDto);
	}
	
	@PostMapping("/subcategoria")
	@Operation(summary = "Create new Subcategoria", 
    description = "Create a new subcategory in the system.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public SubcategoriaResponse create(@RequestBody @Valid SubcategoriaDto subcategoriaDto) {
		return categoriaSubcategoriaService.createSubcategoria(subcategoriaDto);
	}
	
	@PostMapping("/assign")
	@Operation(summary = "Assign subcategories to a category")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public List<CategoriaResponse> assign(@RequestBody @Valid CategoriaSubcategoriaDto catSubDto) {
		return categoriaSubcategoriaService.assignSubcategoriesToCategory(catSubDto);
	}
}
