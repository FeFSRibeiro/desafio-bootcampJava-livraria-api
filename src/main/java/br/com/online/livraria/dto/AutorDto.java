package br.com.online.livraria.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AutorDto {
	
	private Long id;
	private String nome;
	private String email;
	private LocalDate dataNascimento;
}
