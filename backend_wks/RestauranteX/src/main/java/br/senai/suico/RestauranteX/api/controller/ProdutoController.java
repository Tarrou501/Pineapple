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

import br.senai.suico.RestauranteX.model.entity.Produto;
import br.senai.suico.RestauranteX.service.impl.ProdutoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	@Autowired
	ProdutoServiceImpl servico;
	
	@Operation(summary = "Find All Products")
	@GetMapping
	public List<Produto> buscar()
	{		
		return  servico.buscar();
	}

	@Operation(summary = "Find Product by Id")
	@GetMapping("/{id}")
	public Optional<Produto> buscaPorId(@PathVariable long id) {
		return servico.buscarPorId(id);
	}

	@PostMapping("/cadastrar")
	public Produto createProduto(@RequestBody Produto produto) {
		return servico.salvar(produto);
	}
	
	@Operation(summary = "Find Product by Name")
	@GetMapping("/buscar")
	public Optional<Produto> findProduto(@RequestBody Produto produto) {
		return servico.buscarPorNome(produto.getNome());
	}
	
	@PutMapping("/{id}")
	public Produto updateProduto(@RequestBody Produto prod, @PathVariable long id) {
		    if (id != prod.getId()) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        }
	        prod.setId(id);
	        return servico.atualizar(prod);
	}
	
	@DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirPorId(@PathVariable long id) {   
        servico.deletar(id);
    }

}
