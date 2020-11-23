package br.com.gmissio.controleestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmissio.controleestoque.controller.dto.CategoriaDto;
import br.com.gmissio.controleestoque.controller.dto.ClienteDto;
import br.com.gmissio.controleestoque.model.Categoria;
import br.com.gmissio.controleestoque.model.Cliente;
import br.com.gmissio.controleestoque.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public Page<CategoriaDto> lista(@RequestParam(required = false) String nome, //posteriormente filtar 
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		System.out.println(nome);
		//para fazer com filtro de cliente
		if (nome == null) {
			Page<Categoria> categoria = categoriaRepository.findAll(paginacao);
			return CategoriaDto.converter(categoria);
		} else {
			Page<Categoria> categoria = categoriaRepository.findByNome(nome, paginacao);
			return CategoriaDto.converter(categoria);
		}
	}
	
}
