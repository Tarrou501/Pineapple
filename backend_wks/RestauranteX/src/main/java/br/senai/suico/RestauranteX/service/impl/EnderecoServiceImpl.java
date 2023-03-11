package br.senai.suico.RestauranteX.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
	public Endereco salvar(Endereco endereco) {
		return repository.save(endereco);
	}
	
	

	@Transactional
	@Override
	public Endereco atualizar(Endereco endereco) {
		var EnderecoOptional = repository.findById(endereco.getId());
		if (EnderecoOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return repository.save(endereco);
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
		    repository.delete(EnderecoOptional.get());
		}
		catch(Exception ex ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public List<Endereco> buscar() {
		
		return repository.findAll();
	}

	@Override
	public Optional<Endereco> buscarPorId(long id) {
		
		return repository.findById(id);
	}



}
