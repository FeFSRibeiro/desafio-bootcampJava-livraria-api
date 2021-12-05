package br.com.online.livraria.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginFormDto {
	
	@NotBlank
	private String login;
	@NotBlank
	private String senha;
}
