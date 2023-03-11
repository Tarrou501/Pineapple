package br.senai.suico.RestauranteX.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.suico.RestauranteX.model.entity.Categoria;
import br.senai.suico.RestauranteX.model.entity.Cliente;
import br.senai.suico.RestauranteX.model.entity.Endereco;
import br.senai.suico.RestauranteX.model.entity.Produto;
import br.senai.suico.RestauranteX.model.repository.CategoriaRepository;
import br.senai.suico.RestauranteX.model.repository.ClienteRepository;
import br.senai.suico.RestauranteX.model.repository.EnderecoRepository;
import br.senai.suico.RestauranteX.model.repository.ProdutoRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public void instanciaDB() {
		// ****************** Carga das categorias **********************
		Categoria cat1 = Categoria.builder().nome("Bebidas").build();
		Categoria cat2 = Categoria.builder().nome("Sobremesas").build();
		Categoria cat3 = Categoria.builder().nome("Lanches").build();
		Categoria cat4 = Categoria.builder().nome("Porções").build();

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

		// ******************** Carga das produtos *************************
		Produto prod1 = Produto.builder().nome("Coca Cola").descricao("Refrigerante em Lata").valor(3.5f)
				.imagem_url("cocacola.png").build();
		prod1.setCategoria(cat1);

		Produto prod2 = Produto.builder().nome("Fanta Laranja").descricao("Refrigerante em Lata").valor(3.5f)
				.imagem_url("fantalaranja.png").build();
		prod2.setCategoria(cat1);

		Produto prod3 = Produto.builder().nome("Pudim").descricao("Pudim de Leite Condensado").valor(7.5f)
				.imagem_url("pudimleite.png").build();
		prod3.setCategoria(cat2);

		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		// ********************* Carga dos usuários *************************
		Cliente c2 = Cliente.builder().nome("Luan Silva").email("luan@gmail.com").senha("123").roles("USER").ativo(true).build();
		Cliente c3 = new Cliente(null, "Administrador", "admin@gmail.com", "123", "ADMIN", true, null);

		var c4 = Cliente.builder().nome("Tarrou").email("tarrou@gmail.com").senha("123").roles("ADMIN,USER").ativo(true)
				.build();

		clienteRepository.saveAll(Arrays.asList(c2, c3, c4));

		// ******************** Carga de um cliente com enderecos
		// *************************
		Cliente c1 = new Cliente(null, "Regina Costa", "regina@gmail.com", "123", "USER", true, null);
		Endereco end1 = Endereco.builder().cep("30000000").logradouro("Rua das Flores").numero("12").complemento("casa").bairro("Bairro das Saudades").municipio("São José dos Campos de Itaquá").uf("SP").ativo(true)
				.cliente(c1).build();
		Endereco end2 = Endereco.builder().cep("30000100").logradouro("Rua dos Espinhos").numero("14").complemento("").bairro("Favela do Boi Preto").municipio("São José dos Campos de Itaquá").uf("SP").ativo(true)
				.cliente(c1).build();

		List<Endereco> endlst = new ArrayList<Endereco>();
		endlst.add(end1);
		endlst.add(end2);
		c1.setEnderecos(endlst);

		clienteRepository.saveAll(Arrays.asList(c1));

		 enderecoRepository.saveAll(Arrays.asList(end1,end2));
	}
}
