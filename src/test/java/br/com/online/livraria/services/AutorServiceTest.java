package br.com.online.livraria.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.online.livraria.dto.AutorDto;
import br.com.online.livraria.dto.AutorFormDto;
import br.com.online.livraria.repository.AutorRepository;

@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
	private AutorRepository autorRepository;
	
	@InjectMocks
	private AutorService service;
	
	
	@Test
	void deveriaCadastrarUmAutor() {
		AutorFormDto autorFormDto = new AutorFormDto(
								"Fernanda",
								"fernanda@email.com",
								LocalDate.now(),
								"Teste de Minicurriculo");
		
		AutorDto dto = service.cadastrar(autorFormDto);
		
		assertEquals(autorFormDto.getNome(), dto.getNome());
		assertEquals(autorFormDto.getEmail(), dto.getEmail());
		assertEquals(autorFormDto.getDataNascimento(), dto.getDataNascimento());
			
	}

}
