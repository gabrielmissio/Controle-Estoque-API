package br.com.gmissio.controleestoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmissio.controleestoque.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{

	Page<Fornecedor> findByNome(String nome, Pageable paginacao);

}
