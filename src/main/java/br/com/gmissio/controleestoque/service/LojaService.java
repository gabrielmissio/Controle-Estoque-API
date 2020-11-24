package br.com.gmissio.controleestoque.service;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaLojaForm;
import br.com.gmissio.controleestoque.controller.dto.LojaDto;
import br.com.gmissio.controleestoque.form.LojaForm;
import br.com.gmissio.controleestoque.model.Loja;
import br.com.gmissio.controleestoque.repository.LojaRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;
	
	public Page<LojaDto> read(String nome, Pageable paginacao) {
		
		if (nome == null) {
			Page<Loja> loja = lojaRepository.findAll(paginacao);
			return LojaDto.converter(loja);
		} else {
			Page<Loja> loja = lojaRepository.findByNome(nome, paginacao);
			return LojaDto.converter(loja);
		}
	}
	
	public ResponseEntity<LojaDto> create(LojaForm form, UriComponentsBuilder uriBuilder){
		Loja loja = form.converter(form);
		lojaRepository.save(loja);
		
		URI uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new LojaDto(loja));
	}
	
	public ResponseEntity<LojaDto> getById(Long id){
		
		Optional<Loja> optional = lojaRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new LojaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<LojaDto> update(Long id, AtualizaLojaForm form){
		Optional<Loja> optinal = lojaRepository.findById(id);
		if(optinal.isPresent()) {
			Loja loja = form.atualizar(id, lojaRepository);
			return ResponseEntity.ok(new LojaDto(loja));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<?> delete(Long id){
		Optional<Loja> optional = lojaRepository.findById(id);
		if(optional.isPresent()) {
			lojaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
}
