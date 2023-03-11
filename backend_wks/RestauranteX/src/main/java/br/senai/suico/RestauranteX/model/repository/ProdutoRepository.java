package br.senai.suico.RestauranteX.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.suico.RestauranteX.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	
	Optional<Produto> findByNome(String nome);	
}
