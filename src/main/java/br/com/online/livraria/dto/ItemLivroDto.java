package br.com.online.livraria.dto;

import javax.validation.constraints.Digits;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemLivroDto {

	private String autor;
	private Integer quantidadeLivros;
	@Digits(integer = 2, fraction = 2)
	private Double percentual;
}
