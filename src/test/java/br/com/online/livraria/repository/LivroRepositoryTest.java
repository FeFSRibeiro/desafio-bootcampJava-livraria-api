package br.com.online.livraria.repository;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.online.livraria.dto.ItemLivroDto;
import br.com.online.livraria.modelo.Autor;
import br.com.online.livraria.modelo.Livro;
import br.com.online.livraria.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class LivroRepositoryTest {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private TestEntityManager em; 

	
	@Test
	void deveriaRetornarRelatorioDeLivrosPorAutor() {
		
		
		Usuario usuario = new Usuario("Fernanda","feribeiro","123456");
		usuario.adicionarPerfil(null);
		em.persist(usuario);
		
		
		Autor autor = new Autor(
				"Fernanda",
				"fernanda@email.com",
				LocalDate.of(1987, 7, 10),
				"Teste Minicurriculo");
		
		em.persist(autor);
		
		
		Autor autor2 = new Autor(
				"José",
				"jose@email.com",
				LocalDate.of(1970, 3, 10),
				"Teste Minicurriculo");
		
		em.persist(autor2);
		
		
		Livro l1 = new Livro (
				"Aprenda Java em 21 dias",
				LocalDate.of(2021, 10, 2),
				110,
				autor,
				usuario
				);
		
		em.persist(l1);
		
		Livro l2 = new Livro (
				"Aprenda C# em 21 dias",
				LocalDate.of(2010, 1, 2),
				110,
				autor2,
				usuario
				);
		
		em.persist(l2);
		
		
		Livro l3 = new Livro (
				"Aprenda Python em 10 dias",
				LocalDate.of(2018, 8, 2),
				110,
				autor,
				usuario
				);
		
		em.persist(l3);
		
		List<ItemLivroDto> relatorio = livroRepository.relatorioLivroPorAutor();
		
		Assertions
		.assertThat(relatorio)
		.hasSize(2)
		.extracting(ItemLivroDto::getNomeAutor, ItemLivroDto::getQuantidadeLivros, ItemLivroDto::getPercentual)
		.containsExactlyInAnyOrder(
				Assertions.tuple("Fernanda",2l , 66.67),
				Assertions.tuple("José",1l , 33.33)
				);

	}

}
