package br.com.gmissio.controleestoque.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gmissio.controleestoque.model.Categoria;

public class CategoriaForm {
	
	@NotEmpty @NotNull
	private String codigo;
	@NotEmpty @NotNull
	private String descricao;
	@NotEmpty @NotNull
	private String nome;
	
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria converter(CategoriaForm form) {
		
		return new Categoria(form.getNome(), form.getCodigo() ,form.getDescricao());
	}

}
