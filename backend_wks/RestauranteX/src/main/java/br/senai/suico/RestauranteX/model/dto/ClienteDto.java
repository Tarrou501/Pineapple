package br.senai.suico.RestauranteX.model.dto;

import br.senai.suico.RestauranteX.model.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

	private String nome;
	
	private String email;
	
	private String token;
	
}
