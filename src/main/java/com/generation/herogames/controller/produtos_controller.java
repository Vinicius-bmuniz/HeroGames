package com.generation.herogames.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

import com.generation.herogames.model.categorias_model;
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
	
	@GetMapping("/all")
	public ResponseEntity<List<produtos_model>> getAllProdutos (){
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<produtos_model> getProdutosById(@PathVariable Long id){
		return produtosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse (ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<produtos_model>> getProdutosByNome(@PathVariable String nome){
		List<produtos_model> checarProd = produtosRepository.findAllBynomeContainingIgnoreCase(nome);
		if(checarProd.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produtosRepository.findAllBynomeContainingIgnoreCase(nome));
	}
	
	@GetMapping ("preco/maior/{valor}")
	public ResponseEntity<List<produtos_model>> getProdutosByValorMaior (@PathVariable BigDecimal valor){
		List<produtos_model> checarProd = produtosRepository.findAllByvalorGreaterThanEqual(valor);
		if(checarProd.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produtosRepository.findAllByvalorGreaterThanEqual(valor));
	}
	
	@GetMapping ("preco/menor/{valor}")
	public ResponseEntity<List<produtos_model>> getProdutosByValorMenor (@PathVariable BigDecimal valor){
		List<produtos_model> checarProd = produtosRepository.findAllByvalorLessThanEqual(valor);
		if(checarProd.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produtosRepository.findAllByvalorLessThanEqual(valor));
	}

	@GetMapping ("between/{valor1}/{valor2}")
	public ResponseEntity<List<produtos_model>> getProdutosBetween (@PathVariable BigDecimal valor1, @PathVariable BigDecimal valor2){
		List<produtos_model> checarProd = produtosRepository.findAllByvalorBetween(valor1, valor2);
		if(checarProd.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(produtosRepository.findAllByvalorBetween(valor1, valor2));
	}
	
	@PostMapping //Verificar como resolver erro 500 ao não enviar o ID da categoria na requisição
	public ResponseEntity<produtos_model> criarProduto(@RequestBody produtos_model produto){
		Optional<categorias_model> checarProd = categoriasRepository.findById(produto.getCategorias().getId());
			if(checarProd.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produto));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping
	public ResponseEntity<produtos_model> editarProduto(@RequestBody produtos_model produto){
		if(produtosRepository.existsById(produto.getId())) {
			return categoriasRepository.findById(produto.getCategorias().getId())
					.map(respo -> ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produto)))
					.orElse(ResponseEntity.badRequest().build());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<?> deleteProduto (@PathVariable Long id){
		return produtosRepository.findById(id)
				.map(delProduto -> {
					produtosRepository.deleteById(id);
					return ResponseEntity.ok(delProduto);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}