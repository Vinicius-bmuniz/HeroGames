package com.generation.herogames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.generation.herogames.model.UsuarioLogin;
import com.generation.herogames.model.usuario_model;
import com.generation.herogames.repository.usuarios_repository;
import com.generation.herogames.service.UsuarioService;

@RequestMapping ("/usuarios")
@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class usuarios_controller {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private usuarios_repository usuariosRepository;
	
	// ===== MÉTODOS SEM RESTRIÇÃO - AUTORIZADOS PELO BasicSecurityConfig ===== //
	@PostMapping("/cadastro")
	public ResponseEntity<usuario_model> cadastrar (@RequestBody usuario_model usuario){ 
		return usuarioService.cadastrarUsuario(usuario)
				.map (respostaCadastro -> ResponseEntity.status(HttpStatus.CREATED).body(respostaCadastro))
				.orElse (ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PostMapping("/logar") 
	public ResponseEntity<UsuarioLogin> logar (@RequestBody Optional<UsuarioLogin> usuarioLogin){
		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(respostaLogin -> ResponseEntity.ok(respostaLogin))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}	
	
	@GetMapping ("/all")
	public ResponseEntity<List<usuario_model>> getAll (){
		return ResponseEntity.ok(usuariosRepository.findAll());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<usuario_model> getById (@PathVariable Long id){
		return usuariosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<usuario_model> atualizar (@Valid @RequestBody usuario_model usuario){
		return usuarioService.atualizarUsuario(usuario)
				.map(respostaAtualizada -> ResponseEntity.ok(respostaAtualizada))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	/*
	 * O método abaixo não faz a verificação se o usuario que está solicitando o Delete
	 * É o mesmo usuário que está sendo deletado.
	 * ADICIONAR POSTERIORMENTE
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario (@PathVariable Long id){ 
		return usuariosRepository.findById(id)
				.map (respostaDeletar -> {
					usuariosRepository.deleteById(id);
					return ResponseEntity.ok(respostaDeletar);
				})
				.orElse (ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
