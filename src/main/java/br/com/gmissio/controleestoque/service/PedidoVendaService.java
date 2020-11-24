package br.com.gmissio.controleestoque.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.gmissio.controleestoque.controller.dto.PedidoVendaDto;
import br.com.gmissio.controleestoque.model.PedidoVenda;
import br.com.gmissio.controleestoque.repository.PedidoVendaRepository;

@Service
public class PedidoVendaService {
	
	@Autowired
	private PedidoVendaRepository pedidoVendaRepository;
	
	public Page<PedidoVendaDto> read(Pageable paginacao){
		
		Page<PedidoVenda> pedidosVendas = pedidoVendaRepository.findAll(paginacao);
		return PedidoVendaDto.converter(pedidosVendas);
	}
	
	public ResponseEntity<PedidoVendaDto> getById(Long id){
		
		Optional<PedidoVenda> optional = pedidoVendaRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new PedidoVendaDto(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

}
