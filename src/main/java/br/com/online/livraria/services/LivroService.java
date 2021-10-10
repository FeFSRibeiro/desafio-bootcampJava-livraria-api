package br.com.online.livraria.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.online.livraria.dto.LivroDto;
import br.com.online.livraria.dto.LivroFormDto;
import br.com.online.livraria.modelo.Livro;
import br.com.online.livraria.repository.LivroRepository;


@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	private List<Livro> livros = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private AutorService autor;
	
	public Page<LivroDto> listar(Pageable paginacao) {
		Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros
				.map(t -> modelMapper.map(t, LivroDto.class));
	}
	
	@Transactional
	public String cadastrar(LivroFormDto dto) {
		Livro livro = modelMapper.map(dto, Livro.class);
		if (autor.verificaAutor(dto.getAutor())) {
			livros.add(livro);
			return "OK";
		}else {
			return "Autor inexistente!";
		}
		
	}
}
