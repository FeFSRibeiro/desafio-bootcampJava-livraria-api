package br.com.online.livraria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.online.livraria.dto.ItemLivroDto;
import br.com.online.livraria.repository.LivroRepository;

@Service
public class RelatorioService {
	
	@Autowired
	private LivroRepository repository; 
	
	public List<ItemLivroDto> relatorioLivroPorAutor(){
		return repository.relatorioLivroPorAutor();
	}


}
