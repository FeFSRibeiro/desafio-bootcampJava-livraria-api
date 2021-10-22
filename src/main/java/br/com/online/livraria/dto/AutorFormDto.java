package br.com.online.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class AutorFormDto {
	
	@NotBlank
	@Size(min = 10)
	private String nome;
	@NotBlank
	private String email;
	@NotNull
	private LocalDate dataNascimento;
	@NotBlank
	private String miniCurriculo;
}
