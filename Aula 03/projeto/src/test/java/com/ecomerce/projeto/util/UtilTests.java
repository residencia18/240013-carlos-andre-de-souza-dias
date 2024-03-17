package com.ecomerce.projeto.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecomerce.projeto.model.Categoria;
import com.ecomerce.projeto.model.Produto;

@SpringBootTest
public class UtilTests {
	
	@Test
	void aplicarDescontoCategoriaTest() {
		Categoria c = new Categoria("Computadores");
		
		Produto p1 = new Produto();
		p1.setNome("Notebook");
		p1.setPreco(2000d);
		
		Produto p2 = new Produto();
		p2.setNome("Monitor");
		p2.setPreco(1000d);
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(p1);
		produtos.add(p2);
		
		c.setProdutos(produtos);
		
		Util.aplicarDescontoCategoria(c, 10f);
		
		Assertions.assertEquals(1800d, c.getProdutos().get(0).getPreco());
		Assertions.assertEquals(900d, c.getProdutos().get(1).getPreco());
	}
	
	@Test
	void aplicarDescontoProdutoTest() {
		Produto p = new Produto();
		p.setNome("Notebook");
		p.setPreco(1000d);
		
		Util.aplicarDescontoProduto(p, 10f);
		
		Assertions.assertEquals(900d, p.getPreco());
	}

}
