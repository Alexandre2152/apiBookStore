package com.ale.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ale.bookstore.domain.Categoria;
import com.ale.bookstore.domain.Livro;
import com.ale.bookstore.dtos.LivrosDTO;
import com.ale.bookstore.repository.CategoriaRepository;
import com.ale.bookstore.repository.LivroRespository;
import com.ale.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRespository repository;

	@Autowired
	private CategoriaService categoriaService;

	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo: " + Livro.class.getName()));
	}

	public List<Livro> findAll(Integer id_cat) {
		categoriaService.findById(id_cat);
		return repository.findAllByCategoria(id_cat);
	}

	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(id_cat);
		obj.setCategoria(cat);
		return repository.save(obj);
	}

	public Livro update(Integer id, LivrosDTO objDto) {
		Livro obj = findById(id);
		updateData(obj, objDto);
		return repository.save(obj);
	}

	private void updateData(Livro obj, LivrosDTO objDto) {
		obj.setTitulo(objDto.getTitulo());
		obj.setNome_autor(objDto.getNome_autor());
		obj.setTexto(objDto.getTexto());
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.ale.bookstore.service.exceptions.DataIntegrityViolationException(
					"Livro não pode ser deletado! Possui objetos associados");
		}
	}
}
