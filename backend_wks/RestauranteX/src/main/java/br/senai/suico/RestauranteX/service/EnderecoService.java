package br.senai.suico.RestauranteX.service;

import java.util.List;
import java.util.Optional;

import br.senai.suico.RestauranteX.model.dto.EnderecoDto;
import br.senai.suico.RestauranteX.model.entity.Endereco;

public interface EnderecoService {


	
	public EnderecoDto salvar(Endereco endereco);
	
	public EnderecoDto atualizar(Endereco endereco);
	public void deletar(long id);

	public EnderecoDto buscarPorId(long id);

}
