package br.com.gmissio.controleestoque.controller.dto;

import org.springframework.data.domain.Page;


import br.com.gmissio.controleestoque.model.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String cpf;
	private String endereco;
	private String cidade;
	
	public ClienteDto(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.endereco = cliente.getEndereco();
		this.cidade = cliente.getCidade();
		this.id = cliente.getId();
	}
	
	public ClienteDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCidade() {
		return cidade;
	}
	
	public static Page<ClienteDto> converter(Page<Cliente> clientes) {
		return clientes.map(ClienteDto::new);
	}
	
}
