package br.senai.suico.RestauranteX.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.senai.suico.RestauranteX.exception.RegraNegocioException;
import br.senai.suico.RestauranteX.model.dto.ClienteDto;
import br.senai.suico.RestauranteX.model.dto.EnderecoDto;
import br.senai.suico.RestauranteX.model.dto.LoginDto;
import br.senai.suico.RestauranteX.model.entity.Cliente;
import br.senai.suico.RestauranteX.model.entity.Endereco;
import br.senai.suico.RestauranteX.model.repository.ClienteRepository;
import br.senai.suico.RestauranteX.model.repository.EnderecoRepository;
import br.senai.suico.RestauranteX.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	
	private ClienteRepository repository;
	private EnderecoRepository repositoryEndereco;
	
	public ClienteServiceImpl(ClienteRepository repository,EnderecoRepository repositoryEndereco) {
		super();
		this.repository = repository;
		this.repositoryEndereco = repositoryEndereco;
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);		
		if(existe) {
			throw new ResponseStatusException(HttpStatus.FOUND);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public void validarDadosObrigatorios(Cliente Cliente) {
		if (Cliente.getNome() == null || Cliente.getNome().trim().equals("")) {
			throw new RegraNegocioException("Informe um nome válido");
		}
		
		if (Cliente.getEmail() == null || Cliente.getEmail().trim().equals("")) {
			throw new RegraNegocioException("Informe um email válido");
		}
		
		if (Cliente.getSenha() == null || Cliente.getSenha().trim().equals("")) {
			throw new RegraNegocioException("Informe uma senha válida");
		}
	}
	
	@Override
	@Transactional
	public ClienteDto salvar(Cliente cliente) {
		validarDadosObrigatorios(cliente);
		validarEmail(cliente.getEmail());	
	
		cliente.setAtivo(true);
		if(cliente.getRoles() == null)
			cliente.setRoles("USER");
		var cli= repository.save(cliente);
		
		atualizarEnderecos(cliente);
		
	
		//cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
	    return cli.toMapperDto();		
	}
	
	private void atualizarEnderecos(Cliente cliente) {
		//this.deletarEnderecoPorClienteId(cliente.getId());
		desativarEnderecosByClienteId(cliente.getId());
		
		var enderecoLst = cliente.getEnderecos();
		if (enderecoLst != null && enderecoLst.size() >0) {			
			enderecoLst.forEach(endClie ->{
				if (endClie.getComplemento() == null) {
					endClie.setComplemento("");
				}
				endClie.setAtivo(true);	
				endClie.setCliente(cliente);
				repositoryEndereco.save(endClie);
			});
		}
	}

	@Override
	public ClienteDto buscarPorEmail(String email) {
		var clienteOptional = repository.findByEmail(email);
		if (clienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return clienteOptional.get().toMapperDto() ;
		
	}

	@Override
	public Optional<LoginDto> autenticar(Cliente cliente) {
		Optional<Cliente> result = repository.findByEmail(cliente.getEmail());
		
		//cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
	    
		if (result.isPresent()) 		{		
			if (result.get().getSenha().equals(cliente.getSenha() )) {
				
				LoginDto cli = new LoginDto();
				cli.setNome(result.get().getNome());
				cli.setEmail(result.get().getEmail());
				cli.setRoles(result.get().getRoles());
				cli.setToken("token123");
				return Optional.of(cli);
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);						
			}
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}	
	}

	@Override
	@Transactional
	public ClienteDto atualizar(Cliente cliente) {
		Objects.requireNonNull(cliente.getId());
		validarDadosObrigatorios(cliente);
		
		var ClienteOptional = repository.findById(cliente.getId());
		if (ClienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		var cli= repository.save(cliente);
		
		atualizarEnderecos(cliente);
		
		return cli.toMapperDto();
	}


	private void desativarEnderecos(Cliente cliente) {		
		var enderecoLst = cliente.getEnderecos();
		if (enderecoLst != null && enderecoLst.size() >0) {			
			enderecoLst.forEach(endClie ->{
				endClie.setAtivo(false);	
				repositoryEndereco.save(endClie);
			});
		}
	}

	private void desativarEnderecosByClienteId(Long id) {
		var clienteOptional = repository.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Cliente cli=  clienteOptional.get();
		
		var listaEnderecos= repositoryEndereco.findByCliente(cli);
		
		listaEnderecos.forEach(ender ->{
			ender.setAtivo(false);	
			repositoryEndereco.save(ender);
		});
	}

	@Override
	@Transactional
	public void deletar(long id) {
		Objects.requireNonNull(id);
		
		var clienteOptional = repository.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		try {
		   // repository.delete(clienteOptional.get());
			var  cliente =clienteOptional.get();
			cliente.setAtivo(false);
			repository.save(cliente);
			desativarEnderecos(cliente);
		}
		catch(Exception ex ){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<ClienteDto> buscarTodos() {
		var listaClientes = repository.findAll();
		
		var listaDto = new ArrayList<ClienteDto>();		
		listaClientes.forEach(cliente -> listaDto.add(cliente.toMapperDto()));
		
		return listaDto;
	}

	@Override
	public ClienteDto buscarPorId(long id) {
		var clienteOptional = repository.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return clienteOptional.get().toMapperDto() ;
	}

	@Override
	public void deletarEnderecoPorClienteId(long id) {
		var enderecosLista =  buscarEnderecoPorClienteId(id);
		enderecosLista.forEach( itemEnde ->{
			repositoryEndereco.deleteById(itemEnde.getId());
			
		});
	}
	
	


	
	@Override
	public List<EnderecoDto> buscarEnderecoPorClienteId(long id) {
	Objects.requireNonNull(id);
		
		var clienteOptional = repository.findById(id);
		if (clienteOptional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Cliente cli=  clienteOptional.get();
		
		var listaEnderecos= repositoryEndereco.findByCliente(cli);
		
		var listaDto = new ArrayList<EnderecoDto>();		
		listaEnderecos.forEach(ender -> listaDto.add(ender.toMapperDto()));
		return listaDto;
	}	
	
}
