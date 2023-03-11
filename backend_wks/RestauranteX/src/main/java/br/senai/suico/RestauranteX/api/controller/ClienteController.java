package br.senai.suico.RestauranteX.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.senai.suico.RestauranteX.model.dto.ClienteDto;
import br.senai.suico.RestauranteX.model.dto.EnderecoDto;
import br.senai.suico.RestauranteX.model.dto.LoginDto;
import br.senai.suico.RestauranteX.model.entity.Cliente;
import br.senai.suico.RestauranteX.model.entity.Endereco;
import br.senai.suico.RestauranteX.service.impl.ClienteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

//@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	@Autowired
	ClienteServiceImpl servico;

	@Operation(summary = "Find All Client")
	@GetMapping
	public List<ClienteDto> buscar() {
		return  servico.buscarTodos();
	
	}
	

	@Operation(summary = "Find All  Client address")
	@GetMapping("/enderecos/{id}")
	public List<EnderecoDto> buscarEndereco(@PathVariable long id) {
		return  servico.buscarEnderecoPorClienteId(id);
	
	}

	@Operation(summary = "Find Client by Id")
	@GetMapping("/{id}")
	public ClienteDto buscarPorId(@PathVariable long id) {
		return  servico.buscarPorId(id);
			
	}

	@PostMapping("/cadastrar")
	public ClienteDto createCliente(@RequestBody Cliente cliente) {
		return servico.salvar(cliente);		
	}

	@Operation(summary = "Find Client by Email")
	@PostMapping("/buscar")
	public ClienteDto findCliente(@RequestBody Cliente cliente) {
		return servico.buscarPorEmail(cliente.getEmail());
	}

	@Operation(summary = "Authentication for Bitte 2.0", responses = {
			@ApiResponse(responseCode = "404", description = "Login not found for support look for Yann") })

	@PostMapping("/autenticar")
	public Optional<LoginDto> autenticar(
			// @Parameter(cli ={"email"="","senha":"123"})
			@RequestBody Cliente cliente) {
		return servico.autenticar(cliente);
	}

	@PutMapping("/{id}")
	public ClienteDto updateCliente(@RequestBody Cliente categ, @PathVariable long id) {
		if (id != categ.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		categ.setId(id);
		return servico.atualizar(categ);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirPorId(@PathVariable long id) {
		servico.deletar(id);
	}

	@DeleteMapping("/enderecos/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirEnderecoPorId(@PathVariable long id) {
		servico.deletarEnderecoPorClienteId(id);
	}
}
