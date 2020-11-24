package br.com.gmissio.controleestoque.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gmissio.controleestoque.controller.dto.FornecedorDto;
import br.com.gmissio.controleestoque.form.FornecedorForm;
import br.com.gmissio.controleestoque.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorService service;
	
	@GetMapping
	public Page<FornecedorDto> readFornecedores(@RequestParam(required = false) String nome,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
		
		return service.read(nome, paginacao);
	}
	
	@Transactional
	@PostMapping
	public ResponseEntity<FornecedorDto> createFornecedor(@RequestBody @Valid FornecedorForm form, UriComponentsBuilder uriBuilder){
		
		return service.create(form, uriBuilder);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDto> getFornecedorById(@PathVariable Long id){
		
		return service.getById(id);
	}
	
	
}
