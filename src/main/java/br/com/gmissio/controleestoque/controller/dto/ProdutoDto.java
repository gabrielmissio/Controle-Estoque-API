package br.com.gmissio.controleestoque.controller.dto;

import org.springframework.data.domain.Page;

import br.com.gmissio.controleestoque.model.Produto;

public class ProdutoDto {
	
	private long id;
	private String descricao;
	private Double precoUnitario;
	private int quantidade;
	private String codigo;
	private String nome_categoria;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.descricao = produto.getDescricao();
		this.precoUnitario = produto.getPrecoUnitario();
		this.quantidade = produto.getQuantidade();
		this.codigo = produto.getCodigo();
		this.nome_categoria = produto.getCategoria().getNome();
	}
	
	public ProdutoDto() {
		
	}
	
	public long getId() {
		return id;
	}
	public String getDescricao() {
		return descricao;
	}
	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}
	
	public static Page<ProdutoDto> converter(Page<Produto> produtos){
		return produtos.map(ProdutoDto::new);
	}
	
	

}
