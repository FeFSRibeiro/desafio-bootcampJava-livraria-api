package br.com.online.livraria.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.online.livraria.dto.AtualizacaoAutorFormDto;
import br.com.online.livraria.dto.AutorDto;
import br.com.online.livraria.dto.AutorFormDto;
import br.com.online.livraria.dto.DetalharAutorDto;
import br.com.online.livraria.modelo.Autor;
import br.com.online.livraria.repository.AutorRepository;


@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	

	private ModelMapper modelMapper = new ModelMapper();
	
	public Page<AutorDto> listar(Pageable paginacao) {
		Page<Autor> autores = autorRepository.findAll(paginacao);
		return autores
				.map(t -> modelMapper.map(t, AutorDto.class));
	}
	
	@Transactional
	public DetalharAutorDto cadastrar(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		
		autorRepository.save(autor);

		return modelMapper.map(autor, DetalharAutorDto.class);
	}

	public Optional<Autor> buscarPorId(Long autorId) {
		return autorRepository.findById(autorId);
	}

	@Transactional
	public AutorDto atualizar(@Valid AtualizacaoAutorFormDto dto) {
		Autor autor = autorRepository.getById(dto.getId()); 
		autor.atualizarInformacoes(dto.getNome(),dto.getEmail(), dto.getDataNascimento(), dto.getMiniCurriculo());
	
		return modelMapper.map(autor, AutorDto.class);
	}
	
	
	@Transactional
	public void remover(@NotNull Long id) {
		autorRepository.deleteById(id);
		
	}
	
	public AutorDto detalhar(@NotNull Long id) {
		Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());  
		return modelMapper.map(autor, AutorDto.class);
	}

	
}
