package br.com.gmissio.controleestoque.controller.dto;

import br.com.gmissio.controleestoque.model.Loja;
import org.springframework.data.domain.Page;


public class LojaDto {

	private long id;
	private String nome;
	private String cnpj;
	private String cidade;
	private String endereco;
	
	
	public LojaDto(Loja loja) {
		this.id = loja.getId();
		this.nome = loja.getNome();
		this.cnpj = loja.getCnpj();
		this.cidade = loja.getCidade();
		this.endereco = loja.getEndereco();
	}
	
	public LojaDto() {
		
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
	
	public static Page<LojaDto> converter(Page<Loja> lojas) {
		return lojas.map(LojaDto::new);
	}
	
}
