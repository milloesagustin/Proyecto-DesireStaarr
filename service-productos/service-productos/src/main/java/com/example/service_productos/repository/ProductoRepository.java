package com.example.service_productos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.service_productos.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{

    List<Producto> findByNombre(String nombre);

}
