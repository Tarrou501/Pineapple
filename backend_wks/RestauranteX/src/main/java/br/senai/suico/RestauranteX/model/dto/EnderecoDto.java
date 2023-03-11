package br.senai.suico.RestauranteX.model.dto;

import java.util.ArrayList;
import java.util.List;

import br.senai.suico.RestauranteX.model.entity.Endereco;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EnderecoDto {
	private Long id;
	private String cep;
	private String logradouro;	
	private String numero;	
	private String complemento;
    private String bairro;
	private String municipio;	
	private String uf;	
	private boolean ativo;
	
	
}
