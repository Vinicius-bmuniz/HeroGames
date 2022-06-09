package com.generation.herogames.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.herogames.model.usuario_model;

@Repository
public interface usuarios_repository extends JpaRepository<usuario_model, Long>{

	//Método para pesquisar pelo usuário (email)
	public Optional<usuario_model> findByUsuario (String usuario);	
}
