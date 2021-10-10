package br.com.online.livraria.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.online.livraria.dto.LivroDto;
import br.com.online.livraria.dto.LivroFormDto;
import br.com.online.livraria.modelo.Autor;
import br.com.online.livraria.modelo.Livro;
import br.com.online.livraria.repository.LivroRepository;


@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private AutorService autorService;
	
	private ModelMapper modelMapper = new ModelMapper();
	

	public Page<LivroDto> listar(Pageable paginacao) {
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros
				.map(t -> modelMapper.map(t, LivroDto.class));
	}
	
	@Transactional
	public LivroDto cadastrar(LivroFormDto dto) {
		Livro livro = modelMapper.map(dto, Livro.class);
		Optional<Autor> autor = autorService.buscarPorId(dto.getAutorId());
		
		if(autor.isEmpty()){
			throw new IllegalArgumentException("Autor não encontrado!");
		}else {
			livro.setAutor(autor.get());
		}		
		livroRepository.save(livro);
		return modelMapper.map(livro, LivroDto.class);
	}
}
