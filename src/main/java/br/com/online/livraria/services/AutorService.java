package br.com.online.livraria.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.online.livraria.dto.AutorDto;
import br.com.online.livraria.dto.AutorFormDto;
import br.com.online.livraria.modelo.Autor;
import br.com.online.livraria.repository.AutorRepository;


@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	private List<Autor> autores = new ArrayList<>();
	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<AutorDto> listar(Pageable paginacao) {
		Page<Autor> autores = autorRepository.findAll(paginacao);
		return autores
				.map(t -> modelMapper.map(t, AutorDto.class));
	}
	
	@Transactional
	public void cadastrar(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autores.add(autor);
	}
	
	
	public boolean verificaAutor(String nomeAutor) {
		return autores
				.stream()
				.anyMatch(autor -> nomeAutor.equals(autor.getNome()));
	}
}
