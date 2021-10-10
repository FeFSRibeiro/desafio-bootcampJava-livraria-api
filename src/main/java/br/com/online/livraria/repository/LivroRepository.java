package br.com.online.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.online.livraria.dto.ItemLivroDto;
import br.com.online.livraria.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	@Query("select new br.com.online.livraria.dto.ItemLivroDto(	"
			+ "a.nome, count(l.titulo), "
			+ "round((count(l.titulo) / (select count(l2.titulo) from Livro l2)*100.0),2)) "
			+ "from Livro l	join Autor a	"
			+ "on(l.autor = a.id)"
			+ "group by a.nome")
	List<ItemLivroDto> relatorioLivroPorAutor();

}
