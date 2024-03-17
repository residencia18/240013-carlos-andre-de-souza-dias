package com.ecomerce.projeto.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.ecomerce.projeto.model.Categoria;
import com.ecomerce.projeto.model.Produto;
import com.ecomerce.projeto.repository.CategoriaRepository;
import com.ecomerce.projeto.repository.ProdutoRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoForm {
	
	private String nome;
	private Double preco;
	private Long categoriaId;
	
	public Produto toProduto(CategoriaRepository repository) {
		Categoria categoria = repository.getReferenceById(categoriaId);
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(categoria);
		
		return new Produto(null, nome, preco, categorias);
	}
	
	public Produto atualizar(Long id, ProdutoRepository produtoRepository, 
			CategoriaRepository categoriaRepository) {
		Produto produto = produtoRepository.getReferenceById(id);
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		
		Categoria categoria = categoriaRepository.getReferenceById(categoriaId);
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(categoria);
		produto.setCategorias(categorias);
		
		return produto;
	}

}
