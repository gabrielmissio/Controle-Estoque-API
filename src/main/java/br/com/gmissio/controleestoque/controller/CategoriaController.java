package br.com.gmissio.controleestoque.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaCategoriaForm;
import br.com.gmissio.controleestoque.controller.dto.CategoriaDto;
import br.com.gmissio.controleestoque.form.CategoriaForm;
import br.com.gmissio.controleestoque.model.Categoria;
import br.com.gmissio.controleestoque.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<CategoriaDto> lista(@RequestParam(required = false) String nome, //posteriormente filtar 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		System.out.println(nome);
		//para fazer com filtro de cliente
		if (nome == null) {
			Page<Categoria> categoria = categoriaRepository.findAll(paginacao);
			return CategoriaDto.converter(categoria);
		} else {
			Page<Categoria> categoria = categoriaRepository.findByNome(nome, paginacao);
			return CategoriaDto.converter(categoria);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder){
		Categoria categoria = form.converter(form);
		categoriaRepository.save(categoria);
		
		//para pegar o caminho da uri e retornar na resposta de requisição
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> detalhar(@PathVariable Long id){
		
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaCategoriaForm form){
		Optional<Categoria> optinal = categoriaRepository.findById(id);
		if(optinal.isPresent()) {
			Categoria categoria = form.converter(id, categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if(optional.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
