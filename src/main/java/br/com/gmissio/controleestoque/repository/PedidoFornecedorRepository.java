package br.com.gmissio.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmissio.controleestoque.model.PedidoFornecedor;

public interface PedidoFornecedorRepository extends JpaRepository<PedidoFornecedor, Long> {

}
