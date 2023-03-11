package br.senai.suico.RestauranteX.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.suico.RestauranteX.model.entity.Cliente;
import br.senai.suico.RestauranteX.model.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	List<Endereco>  findByCliente(Cliente cliente);	
	
	
}
