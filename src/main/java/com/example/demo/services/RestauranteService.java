package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.daos.CategoriaDAO;
import com.example.demo.daos.RestauranteDAO;
import com.example.demo.models.Restaurante;
import com.example.demo.util.Functions;

@Service
@Transactional
public class RestauranteService {

	@Autowired
	private RestauranteDAO repository;
	
	@Autowired
	private CategoriaDAO cateRepository;
	
	public List<Restaurante> listAll(){
		Integer ativo = 1;
		return repository.listAll(ativo);
	}
	
	public void save(Restaurante restaurante) {
		
		//set a hash of user
		String hashId = UUID.randomUUID().toString();
		restaurante.setHashId(hashId);
		
		repository.save(restaurante);
	}
	
	public Restaurante get(Integer idRestaurante) {
		return repository.findById(idRestaurante).get();
	}
	
	public void delete(Integer idRestaurante) {
		repository.deleteById(idRestaurante);
	}
	
	
	public Restaurante buscarConta(String email, String senha) {
		return repository.buscalogin(email, senha);
	}
	
	public Restaurante verificaCPF(String cnpj) {
		return repository.findByCnpj(cnpj);
	}
	
	public Restaurante verificaEmailRest(String email) {
		return repository.findByEmail(email);
	}

	public Restaurante findByHashId(String hashId) {
		return repository.findByHashId(hashId);
	}

	public List<Restaurante> listRestauranteCate(String cate) {
		Integer ativo = 1;
		Boolean categoria = cateRepository.buscarPorNome(cate) == null;
		
		if(categoria != true) {
			return repository.buscarPorNomeCate(cate,ativo);
		}else {			
			return repository.findAll();
		}
		

	}
	
	public void redefinirSenha(String hash, String senha) {
		
		Restaurante restaurante = repository.findByHashId(hash);
		
		String novaSenha = Functions.getSHA256(senha);
		restaurante.setSenha(novaSenha);
		restaurante.setSaltera(0);
		
		repository.save(restaurante);
	}

	public List<Restaurante> pesquisa(String pesquisa) {
		Integer ativo = 1;
		return repository.pesquisa(pesquisa,ativo);
	}
}

