package com.ale.bookstore.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ale.bookstore.domain.Livro;
import com.ale.bookstore.dtos.LivrosDTO;
import com.ale.bookstore.service.LivroService;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livro")
public class LivroResource {

	@Autowired
	private LivroService services;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Integer id) {
		Livro obj = services.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping
	public ResponseEntity<List<LivrosDTO>> findAll(
			@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
		List<Livro> list = services.findAll(id_cat);
		List<LivrosDTO> listDtos = list.stream().map(obj -> new LivrosDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDtos);
	}

	@PostMapping
	public ResponseEntity<Livro> create(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
			@Valid @RequestBody Livro obj) {
		Livro newObj = services.create(id_cat, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livro/{/id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<LivrosDTO> update(@PathVariable Integer id, @Valid @RequestBody LivrosDTO objDto) {
		Livro newObj = services.update(id, objDto);
		return ResponseEntity.ok().body(new LivrosDTO(newObj));
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<LivrosDTO> updatePatch(@PathVariable Integer id, @Valid @RequestBody LivrosDTO objDto) {
		Livro newObj = services.update(id, objDto);
		return ResponseEntity.ok().body(new LivrosDTO(newObj));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}

}
