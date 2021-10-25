package br.com.online.livraria.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.jayway.jsonpath.JsonPath;






@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class LivroControllerTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	
	@Test
	void naoDeveriaCadastrarLivroComDadosIncompletos() throws Exception {
		String json = "{}";
		mvc
		.perform(post("/livros")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void deveriaCadastrarLivroComDadosCompletos() throws Exception {
		String jsonAutor = "{\"nome\":\"Fernanda Ribeiro\",\"email\":\"fernanda@email.com\",\"dataNascimento\":\"1988-07-11\",\"miniCurriculo\":\"Teste de minicurriculo\"}";
		
		String jsonResultado = "{\"nome\":\"Fernanda Ribeiro\",\"email\":\"fernanda@email.com\",\"dataNascimento\":\"1988-07-11\"}";
		
		MvcResult resultado = mvc
		.perform(post("/autores")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonAutor))
		.andExpect(status().isCreated())
		.andExpect(header().exists("Location"))
		.andExpect(content().json(jsonResultado))
		.andReturn();
		
		
		Integer id = JsonPath.read(resultado.getResponse().getContentAsString(), "$.id");
				
		
		
		String jsonLivro = "{\"titulo\":\"Java em 21 dias\",\"dataLancamento\":\"2020-12-18\",\"quantidadePaginas\":110,\"autor_id\":"+id+"}";
		
		String jsonLivroResultado = "{\n  \"titulo\": \"Java em 21 dias\",\n  \"dataLancamento\": \"2020-12-18\",\n  \"quantidadePaginas\": 110,\n  \"autor\": {\n    \"id\": "+id+"\n  }\n}";

		mvc
		.perform(post("/livros")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonLivro))
		.andExpect(status().isCreated())
		.andExpect(header().exists("Location"))
		.andExpect(content().json(jsonLivroResultado))
		;
	}
}
