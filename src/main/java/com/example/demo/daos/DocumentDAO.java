package com.example.demo.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Voucher;


public interface DocumentDAO extends JpaRepository<Voucher, Integer> {

	
	@Query(value =  "select * from voucher where id_usuario_id_usuario = ?1", nativeQuery = true)
	List<Voucher> buscarPorIdUser(Integer idUsuario);
	
	@Query(value =  "select * from voucher where hash_code = ?1", nativeQuery = true)
	Object buscarPorHash(String codi);
	
	@Query(value =  "select * from voucher where hash_code = ?1", nativeQuery = true)
	Voucher buscarPorHash1(String codi);
	

}
