package br.com.gmissio.controleestoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmissio.controleestoque.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	Page<Cliente> findByNome(String nomeCliente, Pageable paginacao);

}
