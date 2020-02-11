package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.daos.DocumentDAO;
import com.example.demo.models.Voucher;


@Service
@Transactional
public class VoucherService {
	
	@Autowired
	private DocumentDAO Dao;
	
	public void save(Voucher voucher) {
		
	
		//set a hash of user
		String hashCode = UUID.randomUUID().toString();
		voucher.setHashCode(hashCode);
		voucher.setStatus(1);
		
		Dao.save(voucher);
	}

	public List<Voucher> buscarPorIdUser(Integer idUsuario) {
		return Dao.buscarPorIdUser(idUsuario);
	}

	public Object buscarPorHASH(String codi) {
		return Dao.buscarPorHash(codi);
	}
	
	public Voucher buscarPorHASH1(String codi) {
		return Dao.buscarPorHash1(codi);
	}
}
