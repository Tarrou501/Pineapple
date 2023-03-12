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

import br.senai.suico.RestauranteX.model.dto.EnderecoDto;
import br.senai.suico.RestauranteX.model.entity.Endereco;
import br.senai.suico.RestauranteX.service.impl.EnderecoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
	
	@Autowired
	EnderecoServiceImpl servico;
	
	

	@Operation(summary = "Find endereco by Id")
	@GetMapping("/{id}")
	public EnderecoDto buscaPorId(@PathVariable long id) {
		return servico.buscarPorId(id);
	}

	@PostMapping("/cadastrar")
	public EnderecoDto createEndereco(@RequestBody Endereco endereco) {
		return servico.salvar(endereco);
	}
	

	@PutMapping("/{id}")
	public EnderecoDto updateEndereco(@RequestBody Endereco endereco, @PathVariable long id) {
		    if (id != endereco.getId()) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        }
		    endereco.setId(id);
	        return servico.atualizar(endereco);
	}
	
	

}