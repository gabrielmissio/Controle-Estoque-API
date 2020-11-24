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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaLojaForm;
import br.com.gmissio.controleestoque.controller.dto.LojaDto;
import br.com.gmissio.controleestoque.form.LojaForm;
import br.com.gmissio.controleestoque.service.LojaService;

@RestController
@RequestMapping("/lojas")
public class LojaController {

	@Autowired LojaService service;
	
	@GetMapping
	public Page<LojaDto> lista(@RequestParam(required = false) String nome, //posteriormente filtar 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		return service.read(nome, paginacao);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LojaDto> cadastrar(@RequestBody @Valid LojaForm form, UriComponentsBuilder uriBuilder){

		return service.create(form, uriBuilder);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LojaDto> detalhar(@PathVariable Long id){
		
		return service.getById(id);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<LojaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaLojaForm form){

		return service.update(id, form);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){

		return service.delete(id);
	}
	

}
