package br.com.online.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.online.livraria.dto.UsuarioDto;
import br.com.online.livraria.dto.UsuarioFormDto;
import br.com.online.livraria.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios")
@Api(tags="Usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping
	@ApiOperation("Listar Usuário")
	public Page<UsuarioDto> listar(@PageableDefault(size = 10 ) Pageable paginacao) {
		return service.listar(paginacao);
	}

	@PostMapping
	@ApiOperation("Cadastrar Usuário")
	public UsuarioDto cadastrar(@RequestBody @Valid UsuarioFormDto dto) {
		return service.cadastrar(dto);
	}
}
