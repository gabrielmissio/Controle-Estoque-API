package br.com.gmissio.controleestoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmissio.controleestoque.model.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long>{

	Page<Loja> findByNome(String nome, Pageable paginacao);

}
