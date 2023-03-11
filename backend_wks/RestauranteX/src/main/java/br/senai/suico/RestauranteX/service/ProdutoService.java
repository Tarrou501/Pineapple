package br.senai.suico.RestauranteX.service;

import java.util.List;
import java.util.Optional;

import br.senai.suico.RestauranteX.model.entity.Produto;

public interface ProdutoService {
	public void validarDadosObrigatorios(Produto Produto);
	public Produto salvar(Produto produto);
	public Optional<Produto> buscarPorNome(String nome);		

	public Produto atualizar(Produto Produto);
	public void deletar(long id);
	public List<Produto> buscar();
	public Optional<Produto>  buscarPorId(long id);
}
