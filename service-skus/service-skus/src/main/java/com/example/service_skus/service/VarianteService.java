package com.example.service_skus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.service_skus.model.VarianteSku;
import com.example.service_skus.repository.VarianteRepository;

import jakarta.transaction.Transactional;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<VarianteSku> listarTodas(){
        return varianteRepository.findAll();
    }


    // Buscar SKU por ID y enriquecer con datos del producto
    public VarianteSku buscarPorId(Long idSku){
        VarianteSku sku = varianteRepository.findById(idSku).orElse(null);
        if(sku!=null){
            return enriquecerConProducto(sku);
        }
        return null;
    }

    public List<VarianteSku> buscarPorProducto(Long idProducto) {
        return varianteRepository.findByIdProducto(idProducto);
    }


    public List<VarianteSku> buscarPorColor(String color) {
        return varianteRepository.findByColor(color);
    }


    public List<VarianteSku> buscarPorTalla(String talla){
        return varianteRepository.findByTalla(talla);
    }

    @Transactional
    public VarianteSku guardar(VarianteSku variante){
        return varianteRepository.save(variante);
    }

    public void eliminar(Long idSku){
        varianteRepository.deleteById(idSku);
    }

    // Método privado que llama al service-productos (puerto 8082)
    // Igual al patrón enriquecerConPaciente del profesor

    private VarianteSku enriquecerConProducto(VarianteSku sku) {
        if (sku.getIdProducto() != null) {
            try {
                Object producto = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8082/productos/" + sku.getIdProducto())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();

                sku.setProducto(producto);
            } catch (Exception e) {
                sku.setProducto("Producto no disponible actualmente");
            }
        }
        return sku;
    }


    
}
