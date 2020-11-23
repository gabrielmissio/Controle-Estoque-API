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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.gmissio.controleestoque.controller.atualizaform.AtualizaLojaForm;
import br.com.gmissio.controleestoque.controller.dto.LojaDto;
import br.com.gmissio.controleestoque.form.LojaForm;
import br.com.gmissio.controleestoque.model.Loja;
import br.com.gmissio.controleestoque.repository.LojaRepository;

@RestController
@RequestMapping("/lojas")
public class LojaController {
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@GetMapping
	public Page<LojaDto> lista(@RequestParam(required = false) String nome, //posteriormente filtar 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		System.out.println(nome);
		//para fazer com filtro de cliente
		if (nome == null) {
			Page<Loja> loja = lojaRepository.findAll(paginacao);
			return LojaDto.converter(loja);
		} else {
			Page<Loja> loja = lojaRepository.findByNome(nome, paginacao);
			return LojaDto.converter(loja);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LojaDto> cadastrar(@RequestBody @Valid LojaForm form, UriComponentsBuilder uriBuilder){
		Loja loja = form.converter(form);
		lojaRepository.save(loja);
		
		//para pegar o caminho da uri e retornar na resposta de requisição
		URI uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new LojaDto(loja));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LojaDto> detalhar(@PathVariable Long id){
		
		Optional<Loja> optional = lojaRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new LojaDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<LojaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaLojaForm form){
		Optional<Loja> optinal = lojaRepository.findById(id);
		if(optinal.isPresent()) {
			Loja loja = form.atualizar(id, lojaRepository);
			return ResponseEntity.ok(new LojaDto(loja));
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Loja> optional = lojaRepository.findById(id);
		if(optional.isPresent()) {
			lojaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	

}
