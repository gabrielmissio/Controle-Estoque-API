package br.com.gmissio.controleestoque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Produto {
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	 
	private String codigo;
	private Double precoUnitario;
	private int quantidade;
	private String descricao;
	@ManyToOne
	private Categoria categoria;
	
	public Produto() {
		
	}
	
	public Produto(String codigo, Double precoUnitario, int quantidade, String descricao, Categoria categoria) {
		this.codigo = codigo;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
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

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
}
