package com.ale.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ale.bookstore.domain.Categoria;
import com.ale.bookstore.domain.Livro;
import com.ale.bookstore.repository.CategoriaRepository;
import com.ale.bookstore.repository.LivroRespository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRespository livroRespository;

	public void instaciaBaseDeDados() {
		
		//Instaciar as categorias
		Categoria cat1 = new Categoria(null, "Informatica", "Livros de TI");
		Categoria cat2 = new Categoria(null, "Ficção Cientifca", "Ficção Cientifica");
		Categoria cat3 = new Categoria(null, "Biografias", "Livors de Biografias");
		
		//Instaciar os livros ligando com as categorias
		Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
		Livro l2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem ipsum", cat1);
		Livro l3 = new Livro(null, "The Time Machine", "H.G. Wells", "Lorem ipsum", cat2);
		Livro l4 = new Livro(null, "The War of the Word", "H.G Wells", "Lorem ipsum", cat2);
		Livro l5 = new Livro(null, "I, Robot", "Issaac Asimov", "Lorem ipsum",cat2);
		
		//Instaciar as categorias para conhecer os livros e os livros conhecerem as categorias
		cat1.getLivros().addAll(Arrays.asList(l1, l2));
		
		//Persistir as informações no bando de dados
		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.livroRespository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
	}
}
