package br.com.gmissio.controleestoque.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmissio.controleestoque.controller.atualizaform.AtualizarClienteForm;
import br.com.gmissio.controleestoque.controller.dto.ClienteDto;
import br.com.gmissio.controleestoque.form.ClienteForm;
import br.com.gmissio.controleestoque.model.Cliente;
import br.com.gmissio.controleestoque.repository.ClienteRepository;



@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public Page<ClienteDto> lista(@RequestParam(required = false) String nome, //posteriormente filtar 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		System.out.println(nome);
		//para fazer com filtro de cliente
		if (nome == null) {
			Page<Cliente> clientes = clienteRepository.findAll(paginacao);
			return ClienteDto.converter(clientes);
		} else {
			Page<Cliente> clientes = clienteRepository.findByNome(nome, paginacao);
			return ClienteDto.converter(clientes);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
		Optional<Cliente> topico = clienteRepository.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new ClienteDto(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarClienteForm form){
		Cliente cliente = form.atualizar(id, clienteRepository);
		
		return ResponseEntity.ok(new ClienteDto(cliente));
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		Cliente cliente = form.converter(form);
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	
	
	
}
