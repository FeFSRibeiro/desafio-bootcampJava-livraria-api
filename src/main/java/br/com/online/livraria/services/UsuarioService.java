 package br.com.online.livraria.services;

import java.util.Random;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.online.livraria.dto.UsuarioDto;
import br.com.online.livraria.dto.UsuarioFormDto;
import br.com.online.livraria.modelo.Perfil;
import br.com.online.livraria.modelo.Usuario;
import br.com.online.livraria.repository.PerfilRepository;
import br.com.online.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder  bCryptPasswordEncoder;
	
	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios
				.map(u -> modelMapper.map(u, UsuarioDto.class));
	}
	
	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		
		usuario.adicionarPerfil(perfil);
		
		
		String senha = new Random().nextInt(999999)+"";
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));
		UsuarioDto resultado = modelMapper.map(usuarioRepository.save(usuario),UsuarioDto.class);
		return resultado;
	}

}
