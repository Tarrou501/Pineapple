package br.senai.suico.RestauranteX.model.entity;

import java.util.List;

import br.senai.suico.RestauranteX.model.dto.ClienteDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Clientes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@Column(name = "email", nullable = false, length = 100)
	private String email;

	@Column(name = "senha", nullable = false, length = 500)
	private String senha;

	private String roles;

	private boolean ativo;

	public ClienteDto toMapperDto() {	
		var dto=  ClienteDto.builder().id(id).nome(nome).email(email).ativo(ativo).roles(roles).build();
		
		
		/*var enderecosLista = this.getEnderecos();
		if (enderecosLista != null && enderecosLista.size()>0) {
			enderecosLista.forEach( ender ->{
				dto.getEnderecos().add(ender.toMapperDto());	
			});			
		}
		*/
		return dto;
	}

	// você fala pra JPA quem está sendo mapeado, sempre vai passar o nome da
	// variavel
	// @JsonIgnore
	@OneToMany(mappedBy = "cliente",fetch = FetchType.EAGER)
	private List<Endereco> enderecos;// = new ArrayList<Endereco>();

}
