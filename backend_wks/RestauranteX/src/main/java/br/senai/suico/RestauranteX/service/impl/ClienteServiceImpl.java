package br.senai.suico.RestauranteX.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.senai.suico.RestauranteX.exception.RegraNegocioException;
import br.senai.suico.RestauranteX.model.dto.ClienteDto;
import br.senai.suico.RestauranteX.model.entity.Cliente;
import br.senai.suico.RestauranteX.model.repository.ClienteRepository;
import br.senai.suico.RestauranteX.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	
	private ClienteRepository repository;

	public ClienteServiceImpl(ClienteRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Cliente salvar(Cliente Cliente) {
		validar(Cliente);
		return repository.save(Cliente);
	}

	@Override
	@Transactional
	public Cliente atualizar(Cliente Cliente) {
		Objects.requireNonNull(Cliente.getId());
		validar(Cliente);
		
		var ClienteOptional = repository.findById(Cliente.getId());
		if (ClienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return repository.save(Cliente);
	}

	@Override
	@Transactional
	public void deletar(long id) {
		Objects.requireNonNull(id);
		

		var ClienteOptional = repository.findById(id);
		if (ClienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		try {
		    repository.delete(ClienteOptional.get());
		}
		catch(Exception ex ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<Cliente> buscar() {
		return repository.findAll();
	}



	@Override
	@Transactional(readOnly = true)
	public void validar(Cliente Cliente) {
		if (Cliente.getNome() == null || Cliente.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido");
		}
	}


	@Override
	public Optional<ClienteDto> autenticar(Cliente cliente) {
		Optional<Cliente> result = repository.findByEmail(cliente.getEmail());
		
		if (result.isPresent() && result.get().getSenha().equals(cliente.getSenha()))
		{
		
			ClienteDto cli = new ClienteDto();
			cli.setNome(result.get().getNome());
			cli.setEmail(result.get().getEmail());
			cli.setToken("token123");
			return Optional.of(cli);
		}
		
		return  null;
	}

	@Override
	public Optional<Cliente> buscarPorId(long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Cliente> buscarPorEmail(String email) {
		return repository.findByEmail(email);
	}
	


}
