package br.com.gmissio.controleestoque.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmissio.controleestoque.controller.dto.PedidoVendaDto;
import br.com.gmissio.controleestoque.service.PedidoVendaService;

@RestController
@RequestMapping("/pedidos-vendas")
public class PedidoVendaController {

	@Autowired
	private PedidoVendaService service;
	
	@GetMapping
	public Page<PedidoVendaDto> readPedidos(
			@PageableDefault(sort = "id", direction = org.springframework.data.domain.Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){

		return service.read(paginacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoVendaDto> getPedidoById(@PathVariable Long id){
		
		return service.getById(id);
	}
	
	@PostMapping
	@Transactional								//requestBody + valid
	public ResponseEntity<?> createPedidoVenda(Object form, UriComponentsBuilder uriBuildes){
		
		return service.create(form, uriBuildes);
	}
				
	@Transactional
	@PutMapping("/{id}")												//requestBody + valid
	public ResponseEntity<?> updatePedidoVenda(@PathVariable Long id, Object form){
		
		return service.update(id, form);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletePedidoVenda(Long id){
		
		return service.delete(id);
	}
	/*
	 * metodos para produto - pedido
	 * */
	
	
}
