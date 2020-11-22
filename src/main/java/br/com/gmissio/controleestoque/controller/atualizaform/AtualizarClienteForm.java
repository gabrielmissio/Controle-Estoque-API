package br.com.gmissio.controleestoque.controller.atualizaform;

import javax.validation.constraints.NotEmpty;

import org.springframework.lang.NonNull;

import br.com.gmissio.controleestoque.model.Cliente;
import br.com.gmissio.controleestoque.repository.ClienteRepository;

public class AtualizarClienteForm {
	
	@NotEmpty @NonNull
	private String endereco;
	@NotEmpty @NonNull
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
	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);
		cliente.setCidade(this.cidade);
		cliente.setEndereco(this.endereco);
		return cliente;
	}
	
	
	
}
