package com.generation.herogames.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_produtos")
public class produtos_model {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size (min = 5, max = 50, message = "O nome não pode ser menor que 5 e maior que 50 caracteres")
	private String nome;
	
	@NotBlank
	@Size (min = 5, max = 500, message = "A descrição não pode ser menor que 5 e maior que 500 caracteres")
	private String descricao;
	
	@NotNull
	private double valor;
	
	private int quantidade;
	
	@JsonFormat(pattern = "MM/yyyy")
	private Date data_lancamento;
	
	@NotNull
	private boolean disponivel;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private categorias_model categorias;
	
	// ===== Getters and Setters ===== //
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData_lancamento() {
		return data_lancamento;
	}

	public void setData_lancamento(Date data_lancamento) {
		this.data_lancamento = data_lancamento;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public categorias_model getCategorias() {
		return categorias;
	}

	public void setCategorias(categorias_model categorias) {
		this.categorias = categorias;
	}
}

