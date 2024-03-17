package com.ecomerce.projeto.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.ecomerce.projeto.model.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaDTO {
	
	private Long id;
	private String nome;
	private List<ProdutoDTO> produtos;
	
	public CategoriaDTO(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.produtos = new ArrayList<>();
	}

}
