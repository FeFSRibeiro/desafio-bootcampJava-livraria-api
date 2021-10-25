package br.com.online.livraria.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LivroDto {
	
	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private Integer quantidadePaginas;
	private DetalharAutorDto autor;
}
