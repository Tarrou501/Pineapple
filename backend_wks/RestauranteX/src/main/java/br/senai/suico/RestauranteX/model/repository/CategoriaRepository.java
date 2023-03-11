package br.senai.suico.RestauranteX.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.suico.RestauranteX.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

	Optional<Categoria> findByNome(String nome);

}

