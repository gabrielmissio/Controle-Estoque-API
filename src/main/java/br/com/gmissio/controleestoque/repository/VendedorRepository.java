package br.com.gmissio.controleestoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmissio.controleestoque.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

	Page<Vendedor> findByNome(String nome, Pageable paginacao);
	
}
