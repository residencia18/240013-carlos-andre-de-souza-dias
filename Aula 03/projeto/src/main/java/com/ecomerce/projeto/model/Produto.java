package com.ecomerce.projeto.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	
//	@ManyToOne
//	@JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false)
//	private Categoria categoria;
	
	@ManyToMany
	@JoinTable(
	  name = "produto_categoria", 
	  joinColumns = @JoinColumn(name = "produto_id"), 
	  inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> categorias;

}
