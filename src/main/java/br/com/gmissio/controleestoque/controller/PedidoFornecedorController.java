package br.com.gmissio.controleestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmissio.controleestoque.controller.dto.PedidoFornecedorDto;
import br.com.gmissio.controleestoque.service.PedidoFornecedorService;

@RestController
@RequestMapping("/pedidos-fornecedores")
public class PedidoFornecedorController {

	@Autowired
	private PedidoFornecedorService service;
	
	@GetMapping
	public Page<PedidoFornecedorDto> readPedidosFornecedores(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		
		return service.read(paginacao);
	}
	
}
