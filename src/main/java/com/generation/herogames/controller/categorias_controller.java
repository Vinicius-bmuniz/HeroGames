package com.generation.herogames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.herogames.model.categorias_model;
import com.generation.herogames.repository.categorias_repository;

@RestController
@RequestMapping ("/categoria")
public class categorias_controller {
	
	@Autowired
	categorias_repository categoriasRepository;
	
	
	@GetMapping
	public ResponseEntity<List<categorias_model>> getAll (){
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<categorias_model> getById(@PathVariable Long id){
		return categoriasRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse (ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<categorias_model>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(categoriasRepository.findAllBynomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<categorias_model> postCategoria(@RequestBody categorias_model categoria){
		return ResponseEntity.ok(categoriasRepository.save(categoria));
	}
	
	@PutMapping //Colocar tratamento de erro para não criar novo produto caso não exista
	public ResponseEntity<categorias_model> putCategoria(@RequestBody categorias_model categoria){
		categoriasRepository.existsById(categoria.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categoria)); 
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<categorias_model> deleteCategoria(@PathVariable Long id){
		categoriasRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	}
}
