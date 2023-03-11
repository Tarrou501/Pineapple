package br.senai.suico.RestauranteX.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
	private String nome;
	
	private String email;
	
	private String token;
	
	private String roles;	
}
