package com.ecomerce.projeto.util;

import com.ecomerce.projeto.model.Categoria;
import com.ecomerce.projeto.model.Produto;

public class Util {
	
	public static void aplicarDescontoCategoria(Categoria categoria, Float desconto) {
		categoria.getProdutos().forEach(p -> p.setPreco(p.getPreco() - (p.getPreco() * desconto / 100)));
	}
	
	public static void aplicarDescontoProduto(Produto produto, Float desconto) {
		produto.setPreco(produto.getPreco() - (produto.getPreco() * desconto / 100));
	}

}
