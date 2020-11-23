package br.com.gmissio.controleestoque.controller.atualizaform;

import javax.validation.constraints.NotNull;

import br.com.gmissio.controleestoque.model.Vendedor;
import br.com.gmissio.controleestoque.repository.VendedorRepository;

public class AtualizaVendedorForm {

	@NotNull
	private Double salario;
	@NotNull
	private Double comicao;
	public Double getSalario() {
		return salario;
	}
	public Double getComicao() {
		return comicao;
	}
	
	public Vendedor atualizar(Long id, VendedorRepository vendedorRepository) {
		Vendedor vendedor = vendedorRepository.getOne(id);
		vendedor.setComicao(this.comicao);
		vendedor.setSalario(this.salario);
		
		return vendedor;
	}
	
}
