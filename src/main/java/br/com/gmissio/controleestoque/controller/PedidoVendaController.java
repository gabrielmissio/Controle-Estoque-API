package br.com.gmissio.controleestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.gmissio.controleestoque.controller.dto.PedidoVendaDto;
import br.com.gmissio.controleestoque.service.PedidoVendaService;

@RestController
@RequestMapping("/pedidos-vendas")
public class PedidoVendaController {

	@Autowired
	private PedidoVendaService service;
	
	@GetMapping
	public Page<PedidoVendaDto> lista(
			@PageableDefault(sort = "id", direction = org.springframework.data.domain.Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao){

		return service.read(paginacao);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoVendaDto> detalhar(@PathVariable Long id){
		
		return service.getById(id);
	}
	
}
