package com.generation.herogames.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "tb_usuarios")
public class usuario_model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório!")
	private String nome;
	
	@NotBlank(message = "O e-mail é obrigatório!")
	@Email (message = "Deve ser um e-mail válido!")
	private String usuario;
	
	@NotBlank (message = "A senha é obrigatória!")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres!")
	private String senha;
	
	@Column (name = "data_nascimento")
	@JsonFormat (pattern = "yyyy-MM-dd")
	@NotNull (message = "A data de nascimento é obrigatória!")
	private LocalDate dataNascimento;
	
	private String foto;

	// A classe de usuarios NÃO terá relacionamento com Temas nem Produtos, pois não será um marketplace
	
	// ===== GETTERS AND SETTERS ===== //
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
