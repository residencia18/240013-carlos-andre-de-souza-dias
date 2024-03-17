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

import com.ecomerce.projeto.controller.dto.CategoriaDTO;
import com.ecomerce.projeto.controller.form.CategoriaForm;
import com.ecomerce.projeto.model.Categoria;
import com.ecomerce.projeto.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaRepository repository;
	
	@GetMapping
	public List<Categoria> listarCategorias() {
		List<Categoria> listaCategorias = repository.findAll();
		return listaCategorias;
	}
	
	@PostMapping()
	public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {
		Categoria categoria = form.toCategoria();
		repository.save(categoria);
		
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDTO(categoria));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> pegarPorId(@PathVariable Long id) {
		//Optional -> Pode ser que tenha o registro pode ser que não tenha
		//Elimina o erro caso o parâmetro passado não exista
		Optional<Categoria> categoria = repository.findById(id);
		
		if (categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDTO(categoria.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaForm form) {
		Optional<Categoria> optional = repository.findById(id);
		
		if (optional.isPresent()) {
			Categoria categoria = form.atualizar(id, repository);
			return ResponseEntity.ok(new CategoriaDTO(categoria));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Categoria> optional = repository.findById(id);
		
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
