package com.tfm.tfm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tfm.dto.CategoriaDto;
import com.tfm.tfm.dto.MarcaDto;
import com.tfm.tfm.dto.SubcategoriaDto;
import com.tfm.tfm.response.CategoriaResponse;
import com.tfm.tfm.response.MarcaResponse;
import com.tfm.tfm.response.SubcategoriaResponse;
import com.tfm.tfm.service.CategoriaSubcategoriaService;
import com.tfm.tfm.service.MarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class MarcaController {
	@Autowired private MarcaService marcaService;
	@Autowired private CategoriaSubcategoriaService categoriaSubcategoriaService;

	@PostMapping("/marca")
	@Operation(summary = "Create new Marca", 
    description = "Create a new brand in the system.")

		@ApiResponses(value = { 
				@ApiResponse(responseCode = "200", 
					description = "${api.response-codes.ok.desc}"),
				@ApiResponse(responseCode = "400", 
		            description = "${api.response-codes.badRequest.desc}")
		})
	public MarcaResponse create(@RequestBody @Valid MarcaDto marcaDto) {
		return marcaService.createMarca(marcaDto);
	}
	
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
}
