package br.com.gmissio.controleestoque.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gmissio.controleestoque.model.Fornecedor;

public class FornecedorForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String endereco;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Fornecedor converter(FornecedorForm form) {
		
		return new Fornecedor(form.nome, form.endereco);
	}
	
}
