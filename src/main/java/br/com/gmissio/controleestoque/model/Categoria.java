package br.com.gmissio.controleestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Categoria {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String codigo;
	private String descricao;
	private String nome;
	
	public Categoria() {
		
	}
	
	public Categoria(String nome, String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.nome = nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
