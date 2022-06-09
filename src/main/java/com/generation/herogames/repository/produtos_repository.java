package com.generation.herogames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.herogames.model.produtos_model;

@Repository
public interface produtos_repository extends JpaRepository<produtos_model, Long>{

	public List<produtos_model> findAllBynomeContainingIgnoreCase(@Param("nome")String nome);
	public List<produtos_model> findAllByvalorGreaterThanEqual(@Param("valor") BigDecimal valor);
	public List<produtos_model> findAllByvalorLessThanEqual(@Param("valor") BigDecimal valor);
	public List<produtos_model> findAllByvalorBetween(BigDecimal valor1, BigDecimal valor2);
}