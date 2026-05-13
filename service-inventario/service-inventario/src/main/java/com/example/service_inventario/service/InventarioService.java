package com.example.service_inventario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.service_inventario.model.Inventario;
import com.example.service_inventario.repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Service
public class InventarioService {
   @Autowired
    private InventarioRepository repository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Método ennriquecido
    private void enriquecerConSku(Inventario inventario) {
        try {
            Object skuDatos = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8083/skus/" + inventario.getIdSku())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
            inventario.setSku(skuDatos);
        } catch (Exception e) {
            inventario.setSku(null);
        }
    }
    // Método enriquecido
    public List<Inventario> listarTodo() {
        List<Inventario> lista = repository.findAll();
        lista.forEach(this::enriquecerConSku);
        return lista;
    }

    public Optional<Inventario> buscarPorSku(Long idSku) {
        Optional<Inventario> inv = repository.findByidSku(idSku);
        inv.ifPresent(this::enriquecerConSku); // Si existe, lo enriquecemos
        return inv;
    }

    @Transactional
    public Inventario crearEntrada(Inventario inventario) {
        // Validamos con WebClient que el SKU exista antes de crear el inventario
        Object skuExiste = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/skus/" + inventario.getIdSku())
                .retrieve()
                .bodyToMono(Object.class)
                .block();

        if (skuExiste != null) {
            return repository.save(inventario);
        } else {
            throw new RuntimeException("El SKU " + inventario.getIdSku() + " no existe.");
        }
    }

    @Transactional
    public Inventario actualizarStock(Long idSku, int nuevaCantidad) {
        
<<<<<<< HEAD
        Inventario inv = repository.findByidSku(idSku)
=======
        Inventario inv = repository.findByIdSku(idSku)
>>>>>>> 3839919bb050965f03e4e752303f8a3f4689df8a
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado para el SKU: " + idSku));
        
        inv.setCantidadActual(nuevaCantidad);
        return repository.save(inv);
    }

}
