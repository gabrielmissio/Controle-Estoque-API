package br.com.gmissio.controleestoque.controller;

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
import br.com.gmissio.controleestoque.repository.CategoriaRepository;
import br.com.gmissio.controleestoque.repository.ProdutoRepository;
import br.com.gmissio.controleestoque.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<ProdutoDto> lista(@PathVariable (required = false) String descricao,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		
		return service.read(descricao, paginacao);
	}
	
	@PostMapping
	@Transactional
	ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuildes){

		return service.create(form, uriBuildes);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<ProdutoDto> especificar(@PathVariable Long id){
		
		return service.getById(id);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaProdutoForm form){
		
		return service.update(id, form);	
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoDto> remover(@PathVariable Long id){
		
		return service.delete(id);	
	}
	
}
