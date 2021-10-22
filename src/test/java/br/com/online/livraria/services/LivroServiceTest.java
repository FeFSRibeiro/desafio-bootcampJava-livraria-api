package br.com.online.livraria.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.online.livraria.dto.LivroDto;
import br.com.online.livraria.dto.LivroFormDto;
import br.com.online.livraria.repository.AutorRepository;
import br.com.online.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {
	
	@Mock
	private LivroRepository livroRepository;
	
	@Mock
	private AutorRepository autorRepository;
	
//	@Mock
//	private AutorService autorService;
	
	@InjectMocks
	private LivroService livroService;
	
	@Test
	void deveriaCadastrarUmLivro() {
		
		LivroFormDto livroFormDto = new LivroFormDto(
									"Java para iniciantes",
									LocalDate.of(2020, 9, 10),
									110,
									1l
									);
		
		
		LivroDto livroDto = livroService.cadastrar(livroFormDto);
		
		
		assertEquals(livroFormDto.getTitulo(), livroDto.getTitulo());
		assertEquals(livroFormDto.getDataLancamento(), livroDto.getDataLancamento());
		assertEquals(livroFormDto.getQuantidadePaginas(), livroDto.getQuantidadePaginas());
	}
	
	
	
	@Test
	void naoDeveriaCadastrarUmLivroComAutorInexistente() {
		
		LivroFormDto livroFormDto = new LivroFormDto(
									"Java para iniciantes",
									LocalDate.of(2020, 9, 10),
									110,
									1l
									);
		
		
		Mockito.when(autorRepository.getById(livroFormDto.getAutorId()))
		.thenThrow(EntityNotFoundException.class);
		
		
		assertThrows(IllegalArgumentException.class, () -> livroService.cadastrar(livroFormDto));
	}


}
