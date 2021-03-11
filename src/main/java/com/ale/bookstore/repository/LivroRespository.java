package com.ale.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ale.bookstore.domain.Livro;

@Repository
public interface LivroRespository extends JpaRepository<Livro, Integer> {

}
