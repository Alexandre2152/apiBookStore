package com.ale.bookstore.dtos;

import java.io.Serializable;

import com.ale.bookstore.domain.Livro;

public class LivrosDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String titulo;
	private String nome_autor;
	private String texto;
	public LivrosDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivrosDTO(Livro obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
		this.nome_autor = obj.getNome_autor();
		this.texto = obj.getTexto();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getNome_autor() {
		return nome_autor;
	}
	public void setNome_autor(String nome_autor) {
		this.nome_autor = nome_autor;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
