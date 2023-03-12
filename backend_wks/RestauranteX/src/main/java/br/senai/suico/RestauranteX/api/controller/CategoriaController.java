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

import br.senai.suico.RestauranteX.model.entity.Categoria;
import br.senai.suico.RestauranteX.service.impl.CategoriaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	@Autowired
	CategoriaServiceImpl servico;
	
	@Operation(summary = "Find All Categoria")
	@GetMapping
	public List<Categoria> buscar()
	{		
		return  servico.buscar();
	}

	@Operation(summary = "Find Categoria by Id")
	@GetMapping("/{id}")
	public Optional<Categoria> buscaPorId(@PathVariable long id) {
		return servico.buscarPorId(id);
	}

	@PostMapping
	public Categoria createCategoria(@RequestBody Categoria categoria) {
		return servico.salvar(categoria);
	}
	

	@Operation(summary = "Find Categoria by Nome")
	@GetMapping("/buscar")
	public Optional<Categoria> findCategoria(@RequestBody Categoria categoria) {
		return servico.buscarPorNome(categoria.getNome());
	}
	@PutMapping("/{id}")
	public Categoria updateCategoria(@RequestBody Categoria categ, @PathVariable long id) {
		    if (id != categ.getId()) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	        }
	        categ.setId(id);
	        return servico.atualizar(categ);
	}
	
	@DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public Categoria excluirPorId(@PathVariable long id) {   
	
       return  servico.deletar(id);
    }

}