package br.com.gmissio.controleestoque.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import br.com.gmissio.controleestoque.model.Loja;
import br.com.gmissio.controleestoque.model.Vendedor;
import br.com.gmissio.controleestoque.repository.LojaRepository;

public class VendedorForm {
	
	@NotEmpty @NotNull
	private String nome;
	@NotNull
	private Double salario;
	@NotNull
	private Double comicao;
	@NotEmpty @NotNull
	private String nome_loja;
	
	public String getNome() {
		return nome;
	}


	public Double getSalario() {
		return salario;
	}


	public Double getComicao() {
		return comicao;
	}


	public String getNome_loja() {
		return nome_loja;
	}


	public Vendedor converter(VendedorForm form, LojaRepository lojaRepository) {
		Loja loja = lojaRepository.findByNome(form.nome_loja);
		return new Vendedor(form.getNome(), form.getSalario(), form.getComicao(), loja);
	}

}
