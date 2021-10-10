package br.com.online.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.online.livraria.modelo.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	
}
