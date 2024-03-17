package com.ecomerce.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomerce.projeto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}