package com.ale.bookstore.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ale.bookstore.domain.Livro;

public class LivrosDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo TITULO é requerido!")
	@Length(min = 3, max = 50, message = "O campo TITULO deve ter entre 3 e 50 caracteres!")
	private String titulo;
	
	@NotEmpty(message = "Campo NOE DO AUTOR é requereido!")
	@Length(min = 5, max = 50, message = "O campo NOME DO AUTOR deve ter entre 3 e 50 caracteres!")
	private String nome_autor;
	
	@NotEmpty(message = "Campo TEXTo é requerido!")
	@Length(min = 10, max = 2000000, message = "O campo TEXTO deve ter ente 10 e 2000000 caracteres!")
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
