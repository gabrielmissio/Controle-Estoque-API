package br.com.gmissio.controleestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Vendedor {
	
//	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	 private String nome;
	private Double salario;
	private Double comicao;
	private Loja loja;
	
	public Vendedor() {
		
	}
	
	public Vendedor(String nome, Double salario, Double comicao) {
		this.nome = nome;
		this.salario = salario;
		this.comicao = comicao;
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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Double getComicao() {
		return comicao;
	}

	public void setComicao(Double comicao) {
		this.comicao = comicao;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	
	
}
