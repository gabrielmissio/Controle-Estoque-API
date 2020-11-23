package br.com.gmissio.controleestoque.controller.dto;

import org.springframework.data.domain.Page;

import br.com.gmissio.controleestoque.model.Categoria;

public class CategoriaDto {
	
	private Long id;
	private String nome;
	private String descricao;
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.descricao = categoria.getDescricao();
		this.nome = categoria.getNome();
		this.codigo = categoria.getCodigo();
		
	}
	
	public CategoriaDto() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
