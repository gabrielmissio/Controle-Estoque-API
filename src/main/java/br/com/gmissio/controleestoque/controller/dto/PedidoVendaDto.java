package br.com.gmissio.controleestoque.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.gmissio.controleestoque.model.PedidoVenda;

public class PedidoVendaDto {

	private Long id;
	private String nome_cliente;
	private String nome_vendedor;
	private LocalDateTime dataPedido;
	private Double valorTotal;
	
	public PedidoVendaDto(PedidoVenda pedidoVenda) {
		this.id = pedidoVenda.getId();
		this.nome_cliente = pedidoVenda.getCliente().getNome();
		this.nome_vendedor = pedidoVenda.getVendedor().getNome();
		this.dataPedido  = pedidoVenda.getDataPedido();
		this.valorTotal = pedidoVenda.getValorTotal();
	}
	
	public PedidoVendaDto() {
		
	}

	public Long getId() {
		return id;
	}

	public String getNome_cliente() {
		return nome_cliente;
	}

	public String getNome_vendedor() {
		return nome_vendedor;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}
	
	public static Page<PedidoVendaDto> converter(Page<PedidoVenda> pedidosVendas){
		return pedidosVendas.map(PedidoVendaDto::new);
	}
	
}
