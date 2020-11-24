package br.com.gmissio.controleestoque.service;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaProdutoForm;
import br.com.gmissio.controleestoque.controller.dto.ProdutoDto;
import br.com.gmissio.controleestoque.form.ProdutoForm;
import br.com.gmissio.controleestoque.model.Produto;
import br.com.gmissio.controleestoque.repository.CategoriaRepository;
import br.com.gmissio.controleestoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Page<ProdutoDto> read(String descricao, Pageable paginacao){
		
		if(descricao == null) { 
			Page<Produto> produtos = produtoRepository.findAll(paginacao);
			return ProdutoDto.converter(produtos);
		}else {
			Page<Produto> produtos = produtoRepository.findByDescricao(descricao, paginacao);
			return ProdutoDto.converter(produtos);
		}
		
	}
	
	public ResponseEntity<ProdutoDto> create(ProdutoForm form, UriComponentsBuilder uriBuildes){
		Produto produto = form.converter(form, categoriaRepository);
		produtoRepository.save(produto);
		
		URI uri = uriBuildes.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new ProdutoDto(produto)); 
	}
	
	public ResponseEntity<ProdutoDto> getById(Long id){
		
		Optional<Produto> optional = produtoRepository.findById(id); 
		if(optional.isPresent()) {
			return ResponseEntity.ok(new ProdutoDto(optional.get()));
		}else {
		return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<ProdutoDto> update(Long id, AtualizaProdutoForm form){
		
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			Produto produto = form.atualizar(id, produtoRepository);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
	public ResponseEntity<ProdutoDto> delete(Long id){
		
		Optional<Produto> optional = produtoRepository.findById(id);
		if(optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
			
	}
	
	
	
	
}
