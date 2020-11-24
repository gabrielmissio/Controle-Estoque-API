package br.com.gmissio.controleestoque.controller.atualizaform;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import br.com.gmissio.controleestoque.model.Fornecedor;
import br.com.gmissio.controleestoque.repository.FornecedorRepository;

public class AtualizaFornecedorForm {
	
	@NotNull @NotEmpty
	private String endereco;
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Fornecedor converter(Long id, FornecedorRepository repository) {
		
		Fornecedor fornecedor = repository.getOne(id);
		fornecedor.setEndereco(this.endereco);
		
		return fornecedor;
	}

}
