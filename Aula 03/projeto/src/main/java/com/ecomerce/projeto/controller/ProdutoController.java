package com.ecomerce.projeto.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ecomerce.projeto.controller.dto.ProdutoDTO;
import com.ecomerce.projeto.controller.form.ProdutoForm;
import com.ecomerce.projeto.model.Produto;
import com.ecomerce.projeto.repository.CategoriaRepository;
import com.ecomerce.projeto.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Produto> listarProdutos() {
		List<Produto> listaProdutos = produtoRepository.findAll();
		return listaProdutos;
	}
	
	@PostMapping()
	public ResponseEntity<ProdutoDTO> cadastrar(@RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder) {
		Produto produto = form.toProduto(categoriaRepository);
		produtoRepository.save(produto);
		
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDTO> pegarPorId(@PathVariable Long id) {
		//Optional -> Pode ser que tenha o registro pode ser que não tenha
		//Elimina o erro caso o parâmetro passado não exista
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isPresent()) {
			return ResponseEntity.ok(new ProdutoDTO(produto.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoForm form) {
		Optional<Produto> optional = produtoRepository.findById(id);
		
		if (optional.isPresent()) {
			Produto produto = form.atualizar(id, produtoRepository, categoriaRepository);
			return ResponseEntity.ok(new ProdutoDTO(produto));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Produto> optional = produtoRepository.findById(id);
		
		if (optional.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/categoria/{id}")
	public List<Produto> pegarProdutosPorCategoria(@PathVariable Long id) {
		List<Produto> listaProdutos = produtoRepository.findProdutosByCategoriasId(id);
		return listaProdutos;
	}

}
