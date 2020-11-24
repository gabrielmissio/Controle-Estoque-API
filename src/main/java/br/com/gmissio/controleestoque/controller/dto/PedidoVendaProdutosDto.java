package br.com.gmissio.controleestoque.controller.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.gmissio.controleestoque.model.PedidoVenda;
import br.com.gmissio.controleestoque.model.Produto;

public class PedidoVendaProdutosDto {
	
	private Long id_pedido;
	private List<String> nome_produto;
	
	public PedidoVendaProdutosDto(PedidoVenda pedidoVenda) {
		this.id_pedido = pedidoVenda.getId();
		pedidoVenda.getListProdutos().forEach( (a) -> {nome_produto.add(a.getDescricao());});
	}
	
	public PedidoVendaProdutosDto() {
		
	}
	
	public Long getId_pedido() {
		return id_pedido;
	}
	public List<String> getNome_produto() {
		return nome_produto;
	}
	
	public static Page<PedidoVendaProdutosDto> converter(Page<PedidoVenda> pedidosVendas){
		return pedidosVendas.map(PedidoVendaProdutosDto::new);
	}
	

}
