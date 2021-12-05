package br.com.online.livraria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.online.livraria.modelo.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String login);

	@Query("select u from Usuario u JOIN FETCH u.perfis where u.id = :idUsuario")
	Usuario carregarPorIdComPerfis(Long idUsuario);
	
}
