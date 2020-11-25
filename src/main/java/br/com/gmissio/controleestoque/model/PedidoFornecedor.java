package br.com.gmissio.controleestoque.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class PedidoFornecedor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	private Fornecedor fornecedor;
	@ManyToOne
	private Loja loja;
	@ManyToMany
	@JoinTable(name="pedido_fornecedor_produto", joinColumns = {@JoinColumn(name="pedido_id")}, inverseJoinColumns = {@JoinColumn(name="produto_id")})
	private List<Produto> listProduto;
	private Double valorTotal;
	private LocalDateTime dataPedido;
	
	public PedidoFornecedor() {
		
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	
	
}
