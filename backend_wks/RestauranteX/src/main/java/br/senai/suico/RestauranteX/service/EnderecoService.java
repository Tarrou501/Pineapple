package br.senai.suico.RestauranteX.service;

import java.util.List;
import java.util.Optional;

import br.senai.suico.RestauranteX.model.entity.Endereco;

public interface EnderecoService {


	
	public Endereco salvar(Endereco endereco);
	
	public Endereco atualizar(Endereco endereco);
	public void deletar(long id);
	public List<Endereco> buscar();
	public Optional<Endereco>buscarPorId(long id);

}
