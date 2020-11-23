package br.com.gmissio.controleestoque.controller.atualizaform;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gmissio.controleestoque.model.Loja;
import br.com.gmissio.controleestoque.repository.LojaRepository;

public class AtualizaLojaForm {

	@NotEmpty @NotNull
	private String endereco;
	@NotEmpty @NotNull
	private String cidade;
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
	
	public Loja atualizar(Long id, LojaRepository lojaRepository) {
		Loja loja = lojaRepository.getOne(id);
		loja.setCidade(this.cidade);
		loja.setEndereco(this.endereco);
		return loja;
	}
	
}
