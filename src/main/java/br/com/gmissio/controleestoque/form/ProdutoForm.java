package br.com.gmissio.controleestoque.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gmissio.controleestoque.model.Categoria;
import br.com.gmissio.controleestoque.model.Produto;
import br.com.gmissio.controleestoque.repository.CategoriaRepository;

public class ProdutoForm {
	
	@NotNull @NotEmpty
	private String descricao;
	@NotNull
	private Double precoUnitario;
	@NotNull
	private int quantidade;
	@NotNull @NotEmpty
	private String codigo;
	@NotNull @NotEmpty
	private String nome_categoria;
	
	public String getDescricao() {
		return descricao;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome_categoria() {
		return nome_categoria;
	}

	public ProdutoForm() {
		
	}
	
	public Produto converter(ProdutoForm form, CategoriaRepository categoriaRepository) {
		
		Categoria categoria = categoriaRepository.findByNome(nome_categoria);
		
		return new Produto(form.getCodigo(), this.getPrecoUnitario(), form.getQuantidade(), form.getDescricao(), categoria );
	}

}
