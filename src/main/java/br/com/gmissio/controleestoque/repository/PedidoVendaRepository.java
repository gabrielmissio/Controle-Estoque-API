package br.com.gmissio.controleestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmissio.controleestoque.model.PedidoVenda;

public interface PedidoVendaRepository extends JpaRepository<PedidoVenda, Long>{

}
