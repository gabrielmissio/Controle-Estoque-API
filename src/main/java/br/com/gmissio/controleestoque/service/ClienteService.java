package br.com.gmissio.controleestoque.service;

import java.net.URI;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmissio.controleestoque.controller.atualizaform.AtualizarClienteForm;
import br.com.gmissio.controleestoque.controller.dto.ClienteDto;
import br.com.gmissio.controleestoque.form.ClienteForm;
import br.com.gmissio.controleestoque.model.Cliente;
import br.com.gmissio.controleestoque.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Page<ClienteDto> read (String nome, Pageable paginacao){
		if (nome == null) {
			Page<Cliente> clientes = clienteRepository.findAll(paginacao);
			return ClienteDto.converter(clientes);
		} else {
			Page<Cliente> clientes = clienteRepository.findByNome(nome, paginacao);
			return ClienteDto.converter(clientes);
		}
	}
	
	public ResponseEntity<ClienteDto> create(ClienteForm form, UriComponentsBuilder uriBuilder){
		Cliente cliente = form.converter(form);
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	public ResponseEntity<ClienteDto> getById(Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new ClienteDto(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<ClienteDto> update(Long id, AtualizarClienteForm form){
		Optional<Cliente> optional = clienteRepository.findById(id);
		if(optional.isPresent()) {
			Cliente cliente = form.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	public ResponseEntity<?> delete(Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
