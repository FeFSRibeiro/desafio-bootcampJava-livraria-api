package br.com.online.livraria.dto;

import java.time.LocalDate;

import br.com.online.livraria.modelo.Autor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LivroDto {
	
	private Long id;
	private String titulo;
	private LocalDate dataLancamento;
	private int quantidadePaginas;
	private Autor autor;
}
