package br.com.gmissio.controleestoque.service;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.gmissio.controleestoque.controller.dto.FornecedorDto;
import br.com.gmissio.controleestoque.form.FornecedorForm;
import br.com.gmissio.controleestoque.model.Fornecedor;
import br.com.gmissio.controleestoque.repository.FornecedorRepository;

@Service
public class FornecedorService {
	
	@Autowired
	private FornecedorRepository repository;
	
	public Page<FornecedorDto> read(String nome, Pageable paginacao){
		
		if(nome == null) {
			Page<Fornecedor> fornecedores = repository.findAll(paginacao);
			return FornecedorDto.converter(fornecedores);
		}else {
			Page<Fornecedor> fornecedores = repository.findByNome(nome, paginacao);
			return FornecedorDto.converter(fornecedores);
		}
		
	}
	
	public ResponseEntity<FornecedorDto> create(FornecedorForm form, UriComponentsBuilder uriBuilder){
		Fornecedor fornecedor = form.converter(form);
		repository.save(fornecedor);
		
		URI uri = uriBuilder.path("/fornecedores/{id}").buildAndExpand(fornecedor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new FornecedorDto(fornecedor));
	}
	
	public ResponseEntity<FornecedorDto> getById(Long id){
		
		Optional<Fornecedor> oprional = repository.findById(id);
		if(oprional.isPresent()) {
			return ResponseEntity.ok(new FornecedorDto(oprional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}

}
