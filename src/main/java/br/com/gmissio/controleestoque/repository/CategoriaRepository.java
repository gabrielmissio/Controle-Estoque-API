package br.com.gmissio.controleestoque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gmissio.controleestoque.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Page<Categoria> findByNome(String nome, Pageable paginacao);

	Categoria findByNome(String nome);

}
