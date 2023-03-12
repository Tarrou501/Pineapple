package br.senai.suico.RestauranteX.model.entity;

import br.senai.suico.RestauranteX.model.dto.ClienteDto;
import br.senai.suico.RestauranteX.model.dto.EnderecoDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Enderecos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cep", nullable = false, length = 8)
	private String cep;

	@Column(name = "logradouro", nullable = false, length = 100)
	private String logradouro;

	@Column(name = "numero", nullable = false, length = 10)
	private String numero;

	@Column(name = "complemento", nullable = false, length = 50)
	private String complemento;

	@Column(name = "bairro", nullable = false, length = 80)
	private String bairro;

	@Column(name = "municipio", nullable = false, length = 100)
	private String municipio;

	@Column(name = "uf", nullable = false, length = 2)
	private String uf;

	private boolean ativo;

	public EnderecoDto toMapperDto() {
		return EnderecoDto.builder().id(id).logradouro(logradouro).numero(numero).complemento(complemento).cep(cep)
				.bairro(bairro).municipio(municipio).uf(uf).ativo(ativo).build();
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
}
