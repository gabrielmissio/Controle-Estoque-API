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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaProdutoForm;
import br.com.gmissio.controleestoque.controller.dto.ProdutoDto;
import br.com.gmissio.controleestoque.form.ProdutoForm;
import br.com.gmissio.controleestoque.model.Produto;
import br.com.gmissio.controleestoque.repository.CategoriaRepository;
import br.com.gmissio.controleestoque.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<ProdutoDto> lista(@PathVariable (required = false) String descricao,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		
		if(descricao == null) { 
			Page<Produto> produtos = produtoRepository.findAll(paginacao);
			return ProdutoDto.converter(produtos);
		}else {
			Page<Produto> produtos = produtoRepository.findByDescricao(descricao, paginacao);
			return ProdutoDto.converter(produtos);
		}
		
	}
	
	@PostMapping
	@Transactional
	ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuildes){
		Produto produto = form.converter(form, categoriaRepository);
		produtoRepository.save(produto);
		
		URI uri = uriBuildes.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProdutoDto(produto)); 
	}
	
	@GetMapping("/{id}")
	ResponseEntity<ProdutoDto> especificar(@PathVariable Long id){
		
		Optional<Produto> optional = produtoRepository.findById(id); 
		if(optional.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(optional.get()));
		}else {
		return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaProdutoForm form){
		
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			Produto produto = form.atualizar(id, produtoRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoDto> remover(@PathVariable Long id){
		
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
}
