package br.com.gmissio.controleestoque.controller.atualizaform;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gmissio.controleestoque.model.Categoria;
import br.com.gmissio.controleestoque.repository.CategoriaRepository;

public class AtualizaCategoriaForm {
	
	@NotEmpty @NotNull
	private String codigo;
	@NotEmpty @NotNull
	private String descricao;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Categoria converter(Long id, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.getOne(id);
		categoria.setCodigo(this.codigo);
		categoria.setDescricao(this.descricao);
		
		return categoria;
	}

}
