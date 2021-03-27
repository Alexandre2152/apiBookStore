package com.ale.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ale.bookstore.domain.Livro;
import com.ale.bookstore.dtos.LivrosDTO;
import com.ale.bookstore.repository.LivroRespository;
import com.ale.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRespository repository;

	public Livro findById(Integer id) {
		Optional<Livro> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", tipo: " + Livro.class.getName()));
	}

	public List<Livro> findAll() {
		return repository.findAll();
	}

	public Livro create(Livro obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Livro update(Integer id, LivrosDTO objDto) {
		Livro obj = findById(id);
		obj.setTitulo(objDto.getTitulo());
		obj.setNome_autor(objDto.getNome_autor());
		obj.setTexto(objDto.getTexto());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.ale.bookstore.service.exceptions.DataIntegrityViolationException("Livro não pode ser deletado! Possui objetos associados");
		}
	}
}
