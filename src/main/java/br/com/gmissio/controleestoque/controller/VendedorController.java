package br.com.gmissio.controleestoque.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaVendedorForm;
import br.com.gmissio.controleestoque.controller.dto.VendedorDto;
import br.com.gmissio.controleestoque.form.VendedorForm;
import br.com.gmissio.controleestoque.service.VendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	@Autowired
	private VendedorService service;
	
	@GetMapping
	public Page<VendedorDto> readVendederos(@RequestParam (required = false) String nome,
			@PageableDefault(sort = "id", direction = org.springframework.data.domain.Sort.Direction.ASC, page = 0, size = 10 ) Pageable paginacao){
		
		return service.read(nome, paginacao);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VendedorDto> createVendedor(@RequestBody @Valid VendedorForm form, UriComponentsBuilder uriBuilder){

		return service.create(form, uriBuilder);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VendedorDto> getVendedorById(@PathVariable Long id){

		return service.getById(id);
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<VendedorDto> updateVendedor(@PathVariable Long id, @RequestBody @Valid AtualizaVendedorForm form){
		
		return service.update(id, form);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVendedor(@PathVariable Long id){

		return service.delete(id);
	}
	
	
}
