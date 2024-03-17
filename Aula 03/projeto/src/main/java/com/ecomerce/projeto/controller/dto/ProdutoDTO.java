package com.ecomerce.projeto.controller.dto;

import java.util.List;

import com.ecomerce.projeto.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoDTO {
	
	private Long id;
	private String nome;
	private Double preco;
	private List<CategoriaDTO> categorias;
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.categorias = produto.getCategorias().stream().map(CategoriaDTO::new).toList();
	}

}
