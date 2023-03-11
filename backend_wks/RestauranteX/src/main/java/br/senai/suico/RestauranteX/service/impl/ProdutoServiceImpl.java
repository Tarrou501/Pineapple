package br.senai.suico.RestauranteX.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.senai.suico.RestauranteX.exception.RegraNegocioException;
import br.senai.suico.RestauranteX.model.entity.Produto;
import br.senai.suico.RestauranteX.model.repository.ProdutoRepository;
import br.senai.suico.RestauranteX.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	private ProdutoRepository repository;
	public ProdutoServiceImpl(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public void validarDadosObrigatorios(Produto produto) {
		
		if (produto.getNome()== null || produto.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido");
		}
		
		if (produto.getDescricao() == null || produto.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição válida");
		}
		
		if (produto.getImagem_url() == null || produto.getImagem_url().trim().equals("")) {
			throw new RegraNegocioException("Informe um endereço de imagem válido");
		}
		
		if (produto.getValor() <= 0) {
			throw new RegraNegocioException("Informe um valor válido");
		}
	}
	
	@Override
	public Produto salvar(Produto produto) {
		validarDadosObrigatorios(produto);
		//validarNome(produto.getNome());
		return repository.save(produto);
	}

	@Override
	public Optional<Produto> buscarPorNome(String nome) {
		
		return repository.findByNome(nome);
	}
	
	@Transactional
	@Override
	public Produto atualizar(Produto produto) {
		Objects.requireNonNull(produto.getId());
		validarDadosObrigatorios(produto);
		
		var ProdutoOptional = repository.findById(produto.getId());
		if (ProdutoOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return repository.save(produto);
	}

	@Override
	@Transactional
	public void deletar(long id) {
	Objects.requireNonNull(id);
		
		var ProdutoOptional = repository.findById(id);
		if (ProdutoOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		try {
		    repository.delete(ProdutoOptional.get());
		}
		catch(Exception ex ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}
		
	@Override
	public List<Produto> buscar() {
		
		return repository.findAll();
	}

	@Override
	public Optional<Produto> buscarPorId(long id) {
		return repository.findById(id);
	}

}
