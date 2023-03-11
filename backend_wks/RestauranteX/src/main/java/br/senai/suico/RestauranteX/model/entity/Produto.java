package br.senai.suico.RestauranteX.model.entity;

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

@Entity(name="Produtos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "descricao", nullable = false, length = 50)
	private String descricao;
	
	@Column(columnDefinition = "decimal(13,2)")
	private float valor;
	
	@Column(name = "imagem_url", nullable = false, length = 100)
	private String imagem_url;
	
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="categoria_Id")
	private Categoria categoria;
	
		
	
}
