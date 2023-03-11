package br.senai.suico.RestauranteX.service;

import java.util.List;
import java.util.Optional;

import br.senai.suico.RestauranteX.model.dto.ClienteDto;
import br.senai.suico.RestauranteX.model.dto.EnderecoDto;
import br.senai.suico.RestauranteX.model.dto.LoginDto;
import br.senai.suico.RestauranteX.model.entity.Cliente;
import br.senai.suico.RestauranteX.model.entity.Endereco;

public interface ClienteService {	
	public void validarEmail(String email);	
	public void validarDadosObrigatorios(Cliente cliente);
	public ClienteDto salvar(Cliente cliente);
	public ClienteDto  buscarPorEmail(String email);		
	public Optional<LoginDto> autenticar(Cliente cliente);
	
	public void deletarEnderecoPorClienteId(long id);
	public List<EnderecoDto> buscarEnderecoPorClienteId(long id);
	
	
	public ClienteDto atualizar(Cliente cliente);
	public void deletar(long id);
	public List<ClienteDto> buscarTodos();
	public ClienteDto  buscarPorId(long id);
}