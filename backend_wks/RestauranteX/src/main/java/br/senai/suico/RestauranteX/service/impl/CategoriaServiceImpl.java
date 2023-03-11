package br.senai.suico.RestauranteX.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.senai.suico.RestauranteX.exception.RegraNegocioException;
import br.senai.suico.RestauranteX.model.entity.Categoria;
import br.senai.suico.RestauranteX.model.repository.CategoriaRepository;
import br.senai.suico.RestauranteX.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaRepository repository;
	public CategoriaServiceImpl(CategoriaRepository repository) {
		super();
		this.repository = repository;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public void validarDadosObrigatorios(Categoria categoria) {
		if (categoria.getNome()== null || categoria.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido");
		}
	}

	@Override
	public Categoria salvar(Categoria categoria) {
		validarDadosObrigatorios(categoria);
		return repository.save(categoria);
	}

	@Override
	public Optional<Categoria> buscarPorNome(String nome) {
	
		return repository.findByNome(nome);
	}

	@Transactional
	@Override
	public Categoria atualizar(Categoria categoria) {
		Objects.requireNonNull(categoria.getId());
		validarDadosObrigatorios(categoria);
		
		var CategoriaOptional = repository.findById(categoria.getId());
		if (CategoriaOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return repository.save(categoria);
	}

	@Override
	@Transactional
	public void deletar(long id) {
		Objects.requireNonNull(id);
		
		var CategoriaOptional = repository.findById(id);
		if (CategoriaOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		try {
		    repository.delete(CategoriaOptional.get());
		}
		catch(Exception ex ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public List<Categoria> buscar() {
		
		return repository.findAll();
	}

	@Override
	public Optional<Categoria> buscarPorId(long id) {
	
		return repository.findById(id);
	}

	
}
