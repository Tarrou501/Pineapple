package br.senai.suico.RestauranteX.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.senai.suico.RestauranteX.model.dto.EnderecoDto;
import br.senai.suico.RestauranteX.model.entity.Endereco;
import br.senai.suico.RestauranteX.model.repository.EnderecoRepository;
import br.senai.suico.RestauranteX.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	private EnderecoRepository repository;
	public EnderecoServiceImpl(EnderecoRepository repository) {
		this.repository = repository;
	}

	@Override
	public EnderecoDto salvar(Endereco endereco) {
		return repository.save(endereco).toMapperDto();
	}
	
	

	@Transactional
	@Override
	public EnderecoDto atualizar(Endereco endereco) {
		var EnderecoOptional = repository.findById(endereco.getId());
		if (EnderecoOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return repository.save(endereco).toMapperDto();
	}

	@Transactional
	@Override
	public void deletar(long id) {
		Objects.requireNonNull(id);
		
		var EnderecoOptional = repository.findById(id);
		if (EnderecoOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		try {
			var ender = EnderecoOptional.get();
			ender.setAtivo(false);				
			repository.save(ender);			
		}
		catch(Exception ex ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}

	

	@Override
	public EnderecoDto buscarPorId(long id) {
		var EnderecoOptional = repository.findById(id);
		if (EnderecoOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return EnderecoOptional.get().toMapperDto();
	}



}
