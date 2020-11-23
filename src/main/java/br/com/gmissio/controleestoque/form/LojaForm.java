package br.com.gmissio.controleestoque.form;

import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import br.com.gmissio.controleestoque.model.Loja;

public class LojaForm {
	
	@NotEmpty @NonNull
	private String nome;
	@NotEmpty @NonNull
	private String cnpj;
	@NotEmpty @NonNull
	private String endereco;
	@NotEmpty @NonNull
	private String cidade;
	
	public LojaForm() {
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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
	
	public Loja converter(LojaForm form) {
		return new Loja(form.getNome(), form.getEndereco(), form.getCidade(),form.getCnpj());
	}

}
