package com.ale.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ale.bookstore.domain.Categoria;
import com.ale.bookstore.domain.Livro;
import com.ale.bookstore.dtos.CategoriaDTO;
import com.ale.bookstore.dtos.LivrosDTO;
import com.ale.bookstore.service.CategoriaService;
import com.ale.bookstore.service.LivroService;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	private LivroService services;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/categorias")
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDtos = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDtos);
	}

	/*
	 * @GetMapping(value="/livros") public ResponseEntity<List<LivrosDTO>>
	 * findAlll(){ List<Livro> listLivros = services.findAll(); List<LivrosDTO>
	 * listLivrosDtos = listLivros.stream().map(obj -> new
	 * LivrosDTO(obj)).collect(Collectors.toList()); return
	 * ResponseEntity.ok().body(listLivrosDtos); }
	 */
	
	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria obj){
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody CategoriaDTO objDto){
		Categoria newObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new CategoriaDTO(newObj));
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
