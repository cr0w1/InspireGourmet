package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{
	
	@Query(value = "select * from categoria where nome_categoria = ?1", nativeQuery = true)
	public Categoria buscarPorNome(String nomeCategoria);
}
