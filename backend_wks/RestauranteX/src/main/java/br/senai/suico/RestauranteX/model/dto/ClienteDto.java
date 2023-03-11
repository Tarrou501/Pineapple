package br.senai.suico.RestauranteX.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
	private Long id;

	private String nome;

	private String email;

	private String roles;

	private boolean ativo;
	
	private List<EnderecoDto> enderecos = new ArrayList<EnderecoDto>();
}
