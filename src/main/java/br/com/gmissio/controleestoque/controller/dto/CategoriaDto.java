package br.com.gmissio.controleestoque.controller.dto;

import org.springframework.data.domain.Page;

import br.com.gmissio.controleestoque.model.Categoria;

public class CategoriaDto {
	
	private String descricao;
	private String nome;
	
	public CategoriaDto(Categoria categoria) {
		this.descricao = categoria.getDescricao();
		this.nome = categoria.getNome();
	}
	
	public CategoriaDto() {
		
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
	
	public static Page<CategoriaDto> converter(Page<Categoria> categoria) {
		return categoria.map(CategoriaDto::new);
	}


}
