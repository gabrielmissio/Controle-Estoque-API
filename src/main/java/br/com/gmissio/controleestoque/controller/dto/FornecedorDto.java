package br.com.gmissio.controleestoque.controller.dto;

import org.springframework.data.domain.Page;

import br.com.gmissio.controleestoque.model.Fornecedor;

public class FornecedorDto {

	private Long id;
	private String nome;
	private String endereco;
	
	public FornecedorDto(Fornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.nome = fornecedor.getNome();
		this.endereco = fornecedor.getEndereco();
	}
	
	public FornecedorDto() {
		
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getEndereco() {
		return endereco;
	}
	
	public static Page<FornecedorDto> converter(Page<Fornecedor> fornecedores) {
		return fornecedores.map(FornecedorDto::new);
	}

}
