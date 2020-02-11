package com.example.demo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRestaurante;
	
	@Column(length = 150)
	private String nomeRestaurante;
	
	@Column(length = 30)
	private String telefone;
	
	@Column(length = 30)
	private String cnpj;	
	
	@Column(length = 255)
	private String email;
	
	@Column(length = 255)
	private String senha;
	
	private String cep;	
	
	private String bairro;
	
	private String rua;
	
	private String numero;
	
	private String cidade;
	
	private String uf;
	
	private String categoria;
	
	private Integer ativo;
	
	private String hashId;
	
	private Integer prioridade;
	
	private int Saltera;
	
	private String horaAbertura;

	private String horaFechamento;

	private Boolean dA;
	
	private Boolean sA;
	
	private Boolean tA;
	
	private Boolean qA;
	
	private Boolean qiA;
	
	private Boolean sxA;
	
	private Boolean saA;
	
	private Boolean dJ;
	
	private Boolean sJ;
	
	private Boolean tJ;
	
	private Boolean qJ;
	
	private Boolean qiJ;
	
	private Boolean sxJ;
	
	private Boolean saJ	;	
	
	
	public Integer getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(Integer idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public String getNomeRestaurante() {
		return nomeRestaurante;
	}

	public void setNomeRestaurante(String nomeRestaurante) {
		this.nomeRestaurante = nomeRestaurante;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public String getHashId() {
		return hashId;
	}

	public void setHashId(String hashId) {
		this.hashId = hashId;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public int getSaltera() {
		return Saltera;
	}

	public void setSaltera(int saltera) {
		Saltera = saltera;
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}


	
	public String getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(String horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public String getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(String horaFechamento) {
		this.horaFechamento = horaFechamento;
	}

	public Boolean getdA() {
		return dA;
	}

	public void setdA(Boolean dA) {
		this.dA = dA;
	}

	public Boolean getsA() {
		return sA;
	}

	public void setsA(Boolean sA) {
		this.sA = sA;
	}

	public Boolean gettA() {
		return tA;
	}

	public void settA(Boolean tA) {
		this.tA = tA;
	}

	public Boolean getqA() {
		return qA;
	}

	public void setqA(Boolean qA) {
		this.qA = qA;
	}

	public Boolean getQiA() {
		return qiA;
	}

	public void setQiA(Boolean qiA) {
		this.qiA = qiA;
	}

	public Boolean getSxA() {
		return sxA;
	}

	public void setSxA(Boolean sxA) {
		this.sxA = sxA;
	}

	public Boolean getSaA() {
		return saA;
	}

	public void setSaA(Boolean saA) {
		this.saA = saA;
	}

	public Boolean getdJ() {
		return dJ;
	}

	public void setdJ(Boolean dJ) {
		this.dJ = dJ;
	}

	public Boolean getsJ() {
		return sJ;
	}

	public void setsJ(Boolean sJ) {
		this.sJ = sJ;
	}

	public Boolean gettJ() {
		return tJ;
	}

	public void settJ(Boolean tJ) {
		this.tJ = tJ;
	}

	public Boolean getqJ() {
		return qJ;
	}

	public void setqJ(Boolean qJ) {
		this.qJ = qJ;
	}

	public Boolean getQiJ() {
		return qiJ;
	}

	public void setQiJ(Boolean qiJ) {
		this.qiJ = qiJ;
	}

	public Boolean getSxJ() {
		return sxJ;
	}

	public void setSxJ(Boolean sxJ) {
		this.sxJ = sxJ;
	}

	public Boolean getSaJ() {
		return saJ;
	}

	public void setSaJ(Boolean saJ) {
		this.saJ = saJ;
	}


	
	
	
}
	
	
