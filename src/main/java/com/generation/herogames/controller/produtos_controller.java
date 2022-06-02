package com.generation.herogames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.herogames.model.produtos_model;
import com.generation.herogames.repository.categorias_repository;
import com.generation.herogames.repository.produtos_repository;

@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
@RequestMapping ("/produtos")
public class produtos_controller {
	
	@Autowired
	private produtos_repository produtosRepository;
	
	@Autowired
	private categorias_repository categoriasRepository;
	
	@GetMapping
	public ResponseEntity<List<produtos_model>> getAll (){
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<produtos_model> getById(@PathVariable Long id){
		return produtosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse (ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<produtos_model>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(produtosRepository.findAllBynomeContainingIgnoreCase(nome));
	}
	
	@GetMapping ("preco/maior{valor}") //Colocar tratamento de erro.
	public ResponseEntity<List<produtos_model>> getByValorMaior (@PathVariable double valor){
		return ResponseEntity.ok(produtosRepository.findAllByvalorGreaterThanEqual(valor));
	}
	
	@GetMapping ("preco/menor{valor}") //Colocar tratamento de erro.
	public ResponseEntity<List<produtos_model>> getByValorMenor (@PathVariable double valor){
		return ResponseEntity.ok(produtosRepository.findAllByvalorLessThanEqual(valor));
	}

	@GetMapping ("preco/de{valor1}a{valor2}") //Colocar tratamento de erro.
	public ResponseEntity<List<produtos_model>> getByValor (@PathVariable double valor1, @PathVariable double valor2){
		return ResponseEntity.ok(produtosRepository.findAllByvalorBetween(valor1, valor2));
	}
	
	@PostMapping //Verificar como resolver erro ao não enviar o ID da categoria na requisição
	public ResponseEntity<produtos_model> postProduto(@RequestBody produtos_model produto){
		return categoriasRepository.findById(produto.getCategorias().getId())
				.map(save -> ResponseEntity.ok(produtosRepository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PutMapping //Colocar tratamento de erro para não criar novo produto caso não exista
	public ResponseEntity<produtos_model> putProduto(@RequestBody produtos_model produto){
		if (produtosRepository.existsById(produto.getId()) && categoriasRepository.existsById(produto.getCategorias().getId()) ) {
			return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produto));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
		} 
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<produtos_model> deleteProduto(@PathVariable Long id){
		if (produtosRepository.existsById(id)) 
			produtosRepository.deleteById(id);
		
	return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
	
}
}