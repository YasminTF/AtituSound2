package br.atitus.edu.poo.atitusound.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import br.atitus.edu.poo.atitusound.entities.GenericEntity;
import br.atitus.edu.poo.atitusound.services.GenericService;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses(value = {
	@ApiResponse(responseCode = "400", description = "ERRO DE VALIDAÇÃO OU REQUISIÇÃO INVÁLIDA",
			content = @Content, headers = @Header(name = "erro", description = "Descrição de erro", schema = @Schema(implementation = String.class))),
	@ApiResponse(responseCode = "401", description = "UNAUTORIZED", content = @Content),
	@ApiResponse(responseCode = "403", description = "FORBIDDEN", content = @Content)		
  })

public abstract class GenericController <TEntidade extends GenericEntity, TDTO>{
	
	protected abstract GenericService<TEntidade> getserService();
	
	protected abstract TEntidade  convertDto2Entity(TDTO dto);
	
	@PostMapping
	@ApiResponse(responseCode = "201" , description = "REGISTRO CRIADO COM SUCESSO!!")
	public ResponseEntity<TEntidade> postSave(@RequestBody TDTO dto) {
		TEntidade entity = convertDto2Entity(dto);
		try {
			getserService() .save(entity);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(entity);
		}
	
	@GetMapping
	@ApiResponse(responseCode = "200" , description = "OK")
	public ResponseEntity<Page<List<TEntidade>>> getFind(
			@PageableDefault(page = 0, size = 10, sort = "name" , direction = Direction.ASC  ) Pageable pageable,
			@RequestParam String name) {
		Page<List<TEntidade>> lista;
		try {
			 lista = getserService().findByNameContainingIgnoreCase(name, pageable);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
			
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<TEntidade> getFindById(@PathVariable UUID uuid){
		Optional<TEntidade> entity;
		try {
			entity = getserService().findById(uuid);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("error", e.getMessage()).build();
			
		}
		if (entity.isEmpty())
			return ResponseEntity.notFound().build();
		else		
		 return ResponseEntity.ok(entity.get());
		
	}
	
	@PutMapping("/{uuid}")
	public ResponseEntity<TEntidade> put(@PathVariable UUID uuid, @RequestBody TDTO dto) {
		TEntidade entity = convertDto2Entity(dto);
		entity.setUuid(uuid);
		try {
			getserService().save(entity);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("erro", e.getMessage()).build();
			
		}
		return ResponseEntity.ok(entity);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<?> delete(@PathVariable UUID uuid) {
		try {
			getserService().deleteById(uuid);
		} catch (Exception e) {
			return ResponseEntity.badRequest().header("erro", e.getMessage()).build();
		}
		return ResponseEntity.ok().build();
	}


}
