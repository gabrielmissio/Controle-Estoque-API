package br.com.gmissio.controleestoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gmissio.controleestoque.controller.dto.PedidoFornecedorDto;
import br.com.gmissio.controleestoque.model.PedidoFornecedor;
import br.com.gmissio.controleestoque.repository.PedidoFornecedorRepository;

@Service
public class PedidoFornecedorService {

	@Autowired
	private PedidoFornecedorRepository repository;
	
	public Page<PedidoFornecedorDto> read(Pageable paginacao){
		
		Page<PedidoFornecedor> pedidosFornecedores = repository.findAll(paginacao);
		return PedidoFornecedorDto.converter(pedidosFornecedores);
		
	}
	
}
