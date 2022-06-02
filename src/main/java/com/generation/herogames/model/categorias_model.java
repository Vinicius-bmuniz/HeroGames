package com.generation.herogames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categorias")
public class categorias_model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size (min = 1, max = 100, message = "A categoria não pode ter menos de 5 e mais de 100 caracteres")
	private String nome;
	
	@NotBlank
	@Size(min = 5, max = 100, message = "O tipo de categoria não pode ter menos de 5 e mais de 100 caracteres")
	private String tipo;

	@OneToMany (mappedBy = "categorias", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categorias")
	private List<produtos_model> produtos;
	
	// ===== Getters and Setters ==== //
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<produtos_model> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<produtos_model> produtos) {
		this.produtos = produtos;
	}

}
