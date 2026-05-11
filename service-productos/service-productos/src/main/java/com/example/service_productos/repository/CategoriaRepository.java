package com.example.service_productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.service_productos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}
