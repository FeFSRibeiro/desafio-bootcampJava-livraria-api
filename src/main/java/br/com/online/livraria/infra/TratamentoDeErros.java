package br.com.online.livraria.infra;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.online.livraria.dto.Erro400Dto;
import br.com.online.livraria.dto.Erro500Dto;
import javassist.NotFoundException;

@RestControllerAdvice
public class TratamentoDeErros {
	
	@ExceptionHandler({NotFoundException.class,EntityNotFoundException.class, EmptyResultDataAccessException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public void tratarErro404() {
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Erro500Dto tratarErro500 (Exception ex, HttpServletRequest request) {
		return new Erro500Dto (
				LocalDateTime.now(),
				ex.getClass().toString(),
				ex.getMessage(),
				request.getRequestURI());
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus (code = HttpStatus.BAD_REQUEST)
	public List<Erro400Dto> tratarErro400 (MethodArgumentNotValidException ex){
		return ex.getFieldErrors()
				.stream()
				.map(erro -> new Erro400Dto (erro.getField(),erro.getDefaultMessage()))
				.collect(Collectors.toList());
	}
}
