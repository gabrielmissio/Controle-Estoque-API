package br.com.gmissio.controleestoque.controller.dto;

import br.com.gmissio.controleestoque.model.Vendedor;


import org.springframework.data.domain.Page;


public class VendedorDto {
	
	private long id;
	private String nome;
	private String nome_loja;
	private Double salario;
	private Double comicao;
	
	public VendedorDto(Vendedor vendedor) {
		this.id = vendedor.getId();
		this.nome = vendedor.getNome();
		this.nome_loja = vendedor.getLoja().getNome();
		this.salario = vendedor.getSalario();
		this.comicao = vendedor.getComicao();
	}
	
	public VendedorDto() {
		
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
	public String getNome_loja() {
		return nome_loja;
	}
	public void setNome_loja(String nome_loja) {
		this.nome_loja = nome_loja;
	}
	
	public static Page<VendedorDto> converter(Page<Vendedor> vendedores){
		return vendedores.map(VendedorDto::new);
		
	}
	
}
