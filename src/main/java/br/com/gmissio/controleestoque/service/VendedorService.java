package br.com.gmissio.controleestoque.service;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaVendedorForm;
import br.com.gmissio.controleestoque.controller.dto.VendedorDto;
import br.com.gmissio.controleestoque.form.VendedorForm;
import br.com.gmissio.controleestoque.model.Vendedor;
import br.com.gmissio.controleestoque.repository.LojaRepository;
import br.com.gmissio.controleestoque.repository.VendedorRepository;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private LojaRepository lojaRepository;
	
	public Page<VendedorDto> read(String nome,Pageable paginacao){
		
		if(nome == null) {
			Page<Vendedor> vendedores = vendedorRepository.findAll(paginacao);
			return VendedorDto.converter(vendedores);
		}else {
			Page<Vendedor> vendedores = vendedorRepository.findByNome(nome, paginacao);
			return VendedorDto.converter(vendedores);
		}
		
	}
	
	public ResponseEntity<VendedorDto> create(VendedorForm form, UriComponentsBuilder uriBuilder){
		Vendedor vendedor = form.converter(form, lojaRepository);
		vendedorRepository.save(vendedor);
		
		URI uri = uriBuilder.path("/vendedores/{id}").buildAndExpand(vendedor.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VendedorDto(vendedor));
	}
	
	public ResponseEntity<VendedorDto> getById(Long id){
		
		Optional<Vendedor> optional = vendedorRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new VendedorDto(optional.get()));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	public ResponseEntity<VendedorDto> update(Long id, AtualizaVendedorForm form){
		
		Optional<Vendedor> optional = vendedorRepository.findById(id);
		if(optional.isPresent()) {
			Vendedor vendedor = form.atualizar(id, vendedorRepository);
			return ResponseEntity.ok(new VendedorDto(vendedor));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Optional<Vendedor> optinal = vendedorRepository.findById(id);
		if(optinal.isPresent()) {
			vendedorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	

}
