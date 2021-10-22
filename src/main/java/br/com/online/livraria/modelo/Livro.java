package br.com.online.livraria.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livros")
public class Livro{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String titulo;
	
	@Column(name = "data_lancamento")
	private LocalDate dataLancamento;
	
	@Column(name = "quantidade_paginas")
	private Integer quantidadePaginas;
	
	@ManyToOne
	@JoinColumn
	private Autor autor;

	public Livro(String titulo, LocalDate dataLancamento, Integer quantidadePaginas, Autor autor) {
		this.titulo = titulo;
		this.dataLancamento = dataLancamento;
		this.quantidadePaginas = quantidadePaginas;
		this.autor = autor;
	}
	
	
	
	
}
