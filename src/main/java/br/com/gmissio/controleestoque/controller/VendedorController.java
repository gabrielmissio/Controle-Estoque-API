package br.com.gmissio.controleestoque.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import br.com.gmissio.controleestoque.model.Vendedor;
import br.com.gmissio.controleestoque.repository.LojaRepository;
import br.com.gmissio.controleestoque.repository.VendedorRepository;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@GetMapping
	public Page<VendedorDto> lista(@RequestParam (required = false) String nome,
			@PageableDefault(sort = "id", direction = org.springframework.data.domain.Sort.Direction.ASC, page = 0, size = 10 ) Pageable paginacao){
		
		if(nome == null) {
			Page<Vendedor> vendedores = vendedorRepository.findAll(paginacao);
			return VendedorDto.converter(vendedores);
		}else {
			Page<Vendedor> vendedores = vendedorRepository.findByNome(nome, paginacao);
			return VendedorDto.converter(vendedores);
		}
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VendedorDto> cadastrar(@RequestBody @Valid VendedorForm form, UriComponentsBuilder uriBuilder){
		Vendedor vendedor = form.converter(form, lojaRepository);
		vendedorRepository.save(vendedor);
		
		URI uri = uriBuilder.path("/vendedores/{id}").buildAndExpand(vendedor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VendedorDto(vendedor));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VendedorDto> detalhar(@PathVariable Long id){
		
		Optional<Vendedor> optional = vendedorRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new VendedorDto(optional.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<VendedorDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaVendedorForm form){
		
		Optional<Vendedor> optional = vendedorRepository.findById(id);
		if(optional.isPresent()) {
			Vendedor vendedor = form.atualizar(id, vendedorRepository);
			return ResponseEntity.ok(new VendedorDto(vendedor));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
}
