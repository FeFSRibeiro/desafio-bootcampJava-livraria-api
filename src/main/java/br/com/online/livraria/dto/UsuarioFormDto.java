package br.com.online.livraria.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFormDto {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String login;
	
	@NotNull
	@JsonProperty("perfil_id")
	private Long perfilId;

}
