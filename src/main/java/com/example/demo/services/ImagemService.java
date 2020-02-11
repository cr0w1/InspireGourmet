package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.ImagemDAO;
import com.example.demo.models.Imagem;

@Service
public class ImagemService {

	@Autowired
	private ImagemDAO repository;
	
	public Imagem getImagem(Integer idUser) {
		return repository.buscarPorUser(idUser);
	}
	
	public void save(Imagem imagem ) {
		repository.save(imagem);
	}

	public Imagem getImagemRest(Integer idRest) {
		return repository.buscarPorRest(idRest);
	}
}
