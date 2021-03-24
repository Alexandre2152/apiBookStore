package com.ale.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ale.bookstore.domain.Livro;
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
}
