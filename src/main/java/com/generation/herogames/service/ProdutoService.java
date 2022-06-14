package com.generation.herogames.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.herogames.model.produtos_model;
import com.generation.herogames.repository.produtos_repository;

@Service
public class ProdutoService {

	@Autowired
	private produtos_repository produtosRepository;
	
	public Optional <produtos_model> curtir (Long id){
		if (produtosRepository.existsById(id)) {
			produtos_model produto = produtosRepository.findById(id).get();
			produto.setCurtir(produto.getCurtir() + 1);
			return Optional.of(produtosRepository.save(produto));			
		}
		return Optional.empty();
	}
	
	public Optional <produtos_model> descurtir (Long id){
		if (produtosRepository.existsById(id)) {
			produtos_model produto = produtosRepository.findById(id).get();
				if(produto.getCurtir() > 0) {
					produto.setCurtir(produto.getCurtir() - 1);
					return Optional.of(produtosRepository.save(produto));			
			}
		}
		return Optional.empty();
	}
	
}
