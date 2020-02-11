package com.example.demo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Restaurante;

public interface RestauranteDAO extends JpaRepository<Restaurante, Integer> {

	//ISSO FUNCIONA
	@Query(value =  "select * from restaurante where email = ?1 AND senha = ?2", nativeQuery = true)
	Restaurante buscalogin(String email, String senha);

	Restaurante findByHashId(String hashId);
	Restaurante findByCnpj(String cnpj);
	Restaurante findByEmail(String email);

	@Query(value = "select * from restaurante where categoria = ?1 AND ativo = ?2 ORDER BY RAND(id_restaurante) ", nativeQuery = true)
	List<Restaurante> buscarPorNomeCate(String cate ,Integer ativo);

	@Query(value = "select * from restaurante where nome_restaurante like %?1% AND ativo = ?2 ORDER BY RAND(id_restaurante)", nativeQuery = true)
	List<Restaurante> pesquisa(String pesquisa , Integer ativo);
	
	@Query(value = "select * from restaurante where ativo = ?1 ORDER BY RAND(id_restaurante) ", nativeQuery = true)
	List<Restaurante> listAll(Integer ativo);
	
	@Query(value = "select * from restaurante where ativo = ?1 ORDER BY RAND(id_restaurante) ", nativeQuery = true)
	List<Restaurante> buscarPor(String cate ,Integer ativo);
}
