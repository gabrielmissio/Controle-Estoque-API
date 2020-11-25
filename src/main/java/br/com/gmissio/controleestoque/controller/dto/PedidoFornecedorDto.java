package br.com.gmissio.controleestoque.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.gmissio.controleestoque.model.PedidoFornecedor;

public class PedidoFornecedorDto {

	private Long id;
	private String nome_fornecedor;
	private String nome_loja;
	private Double valorTotal;
	private LocalDateTime dataPedido;

	PedidoFornecedorDto(PedidoFornecedor pedido){
		this.id = pedido.getId();
		this.nome_fornecedor = pedido.getFornecedor().getNome();
		this.nome_loja = pedido.getLoja().getNome();
		this.valorTotal = pedido.getValorTotal();
		this.dataPedido = pedido.getDataPedido();
	}
	
	public PedidoFornecedorDto(){
		
	}

	public Long getId() {
		return id;
	}

	public String getNome_fornecedor() {
		return nome_fornecedor;
	}

	public String getNome_loja() {
		return nome_loja;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	
	public static Page<PedidoFornecedorDto> converter(Page<PedidoFornecedor> pedidos){
		
		return pedidos.map(PedidoFornecedorDto::new);
	}
	
}
