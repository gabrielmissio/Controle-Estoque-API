package br.com.gmissio.controleestoque.service;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaCategoriaForm;
import br.com.gmissio.controleestoque.controller.dto.CategoriaDto;
import br.com.gmissio.controleestoque.form.CategoriaForm;
import br.com.gmissio.controleestoque.model.Categoria;
import br.com.gmissio.controleestoque.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<CategoriaDto> read(String nome, Pageable paginacao) {
		
		if (nome == null) {
			Page<Categoria> categoria = categoriaRepository.findAll(paginacao);
			return CategoriaDto.converter(categoria);
		} else {
			Page<Categoria> categoria = categoriaRepository.findByNome(nome, paginacao);
			return CategoriaDto.converter(categoria);
		}
	}
	
	public ResponseEntity<CategoriaDto> create(CategoriaForm form, UriComponentsBuilder uriBuilder){
		Categoria categoria = form.converter(form);
		categoriaRepository.save(categoria);
		
		//para pegar o caminho da uri e retornar na resposta de requisição
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	public ResponseEntity<CategoriaDto> getById(Long id){
		
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<CategoriaDto> update(Long id, AtualizaCategoriaForm form){
		Optional<Categoria> optinal = categoriaRepository.findById(id);
		if(optinal.isPresent()) {
			Categoria categoria = form.atualizar(id, categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<?> delete(Long id){
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if(optional.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
