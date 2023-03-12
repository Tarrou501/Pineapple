package br.senai.suico.RestauranteX.service;

import java.util.List;
import java.util.Optional;

import br.senai.suico.RestauranteX.model.entity.Categoria;


public interface CategoriaService {
	public void validarDadosObrigatorios(Categoria categoria);
	public Categoria salvar(Categoria categoria);
	public Optional<Categoria>buscarPorNome(String nome);		

	public Categoria atualizar(Categoria categoria);
	public Categoria deletar(long id);
	public List<Categoria> buscar();
	public Optional<Categoria>buscarPorId(long id);
}
