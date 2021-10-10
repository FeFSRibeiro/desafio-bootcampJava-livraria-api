package br.com.online.livraria.dto;

import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemLivroDto {

	private String nomeAutor;
	private Long quantidadeLivros;
	@Digits(integer = 2, fraction = 2)
	private Double percentual;
}
