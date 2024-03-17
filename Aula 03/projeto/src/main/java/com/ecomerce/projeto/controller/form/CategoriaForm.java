package com.ecomerce.projeto.controller.form;

import java.util.List;

import com.ecomerce.projeto.model.Categoria;
import com.ecomerce.projeto.model.Produto;
import com.ecomerce.projeto.repository.CategoriaRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoriaForm {
	
	private String nome;
	private List<Produto> produtos;
	
	public Categoria toCategoria() {
		return new Categoria(null, nome, produtos);
	}
	
	public Categoria atualizar(Long id, CategoriaRepository repository) {
		Categoria categoria = repository.getReferenceById(id);
		categoria.setNome(this.nome);
		categoria.setProdutos(this.produtos);
		
		return categoria;
	}

}
