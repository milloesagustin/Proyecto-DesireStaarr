package com.example.service_productos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_productos.model.Producto;
import com.example.service_productos.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id){
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }

    public void eliminar(Long id){
        productoRepository.deleteById(id);
    }
}
