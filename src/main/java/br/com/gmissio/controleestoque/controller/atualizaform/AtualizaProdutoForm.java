package br.com.gmissio.controleestoque.controller.atualizaform;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gmissio.controleestoque.model.Produto;
import br.com.gmissio.controleestoque.repository.ProdutoRepository;

public class AtualizaProdutoForm {

	@NotNull @NotEmpty
	private String descricao;
	@NotNull
	private Double precoUnitario;
	
	public String getDescricao() {
		return descricao;
	}
	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	
	public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
		
		Produto produto = produtoRepository.getOne(id);
		produto.setDescricao(this.descricao);
		produto.setPrecoUnitario(this.precoUnitario);
		
		return produto;
	}
	
}
