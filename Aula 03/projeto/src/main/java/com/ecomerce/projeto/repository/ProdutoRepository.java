package com.ecomerce.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomerce.projeto.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	public List<Produto> findProdutosByCategoriasId(Long id);

}