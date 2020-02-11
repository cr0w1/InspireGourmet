package com.example.demo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Imagem;

public interface ImagemDAO extends JpaRepository<Imagem, Integer> {
	
	@Query(value =  "select * from imagem where id_usuario = ?1", nativeQuery = true)
	Imagem buscarPorUser(Integer idUser);
	
	@Query(value =  "select * from imagem where id_restaurante = ?1", nativeQuery = true)
	Imagem buscarPorRest(Integer idRest);

}
