package br.com.gmissio.controleestoque.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import br.com.gmissio.controleestoque.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired ClienteService service;
	
	@GetMapping
	public Page<ClienteDto> readClientes(@RequestParam(required = false) String nome, //posteriormente filtar 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		return service.read(nome, paginacao);

	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<ClienteDto> createCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		
		return service.create(form, uriBuilder);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {

		return service.getById(id);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody @Valid AtualizarClienteForm form){

		return service.update(id, form);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {

		return service.delete(id);
	}
	
	
}
