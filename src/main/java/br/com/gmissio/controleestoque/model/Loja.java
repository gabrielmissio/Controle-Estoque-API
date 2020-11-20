package br.com.gmissio.controleestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Loja {
	
//	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String endereco;
	private String cidade;
	private String cnpj;
	
	public Loja() {
		
	}
	
	public Loja(String nome, String endereco, String cidade, String cnpj) {
		this.nome = nome;
		this.endereco = endereco;
		this.cidade = cidade;
		this.cnpj = cnpj;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	

}
