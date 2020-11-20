package br.com.gmissio.controleestoque.form;

import br.com.gmissio.controleestoque.model.Cliente;
import br.com.gmissio.controleestoque.repository.ClienteRepository;

public class ClienteForm {

	private String nome;
	private String cpf;
	private String endereco;
	private String cidade;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public Cliente converter(ClienteRepository clienteRepository) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
