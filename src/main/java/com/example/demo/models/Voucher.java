package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Voucher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Usuario idUsuario;
	
	private String nomeUser;
	
	@ManyToOne
	private Oferta idOferta;
	
	private String nomeRest;
	
	private String hashCode;
	
	private int status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public Oferta getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Oferta idOferta) {
		this.idOferta = idOferta;
	}

	public String getNomeRest() {
		return nomeRest;
	}

	public void setNomeRest(String nomeRest) {
		this.nomeRest = nomeRest;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}








	
}
