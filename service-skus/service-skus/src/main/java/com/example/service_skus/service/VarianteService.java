package com.example.service_skus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service_skus.model.VarianteSku;
import com.example.service_skus.repository.VarianteRepository;

import jakarta.transaction.Transactional;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    public List<VarianteSku> listarTodas(){
        return varianteRepository.findAll();
    }

    public Optional<VarianteSku> buscarPorSku(String sku){
        return varianteRepository.findById(sku);
    }

    @Transactional
    public VarianteSku guardar(VarianteSku variante){
        return varianteRepository.save(variante);
    }

    public List<VarianteSku> buscarPorTalla(String talla){
        return varianteRepository.findByTalla(talla);
    }

    public void eliminar(String sku){
        varianteRepository.deleteById(sku);
    }

}
