package com.ale.bookstore;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ale.bookstore.domain.Categoria;
import com.ale.bookstore.domain.Livro;
import com.ale.bookstore.repository.CategoriaRepository;
import com.ale.bookstore.repository.LivroRespository;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRespository livroRespository;

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
		

		@Override
		public void run(String... args) throws Exception {
			
			//Instaciar as categorias
			Categoria cat1 = new Categoria(null, "Informatica", "Livros de TI");
			
			//Instaciar os livros ligando com as categorias
			Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
			
			//Instaciar as categorias para conhecer os livros e os livros conhecerem as categorias
			cat1.getLivros().addAll(Arrays.asList(l1));
			
			//Persistir as informações no bando de dados
			this.categoriaRepository.saveAll(Arrays.asList(cat1));
			this.livroRespository.saveAll(Arrays.asList(l1));
			
		}


}
