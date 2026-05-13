package com.example.service_ventas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.service_ventas.model.Venta;
import com.example.service_ventas.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    public Venta buscarPorId(Long id) {
    Venta venta = ventaRepository.findById(id).orElse(null);
    if (venta != null) {
        enriquecerConCliente(venta);
        if (venta.getDetalles() != null) {
            venta.getDetalles().forEach(detalle -> {
                try {
                    Object sku = webClientBuilder.build()
                            .get()
                            .uri("http://localhost:8083/skus/" + detalle.getSkuId())
                            .retrieve()
                            .bodyToMono(Object.class)
                            .block();
                    detalle.setDatosSku(sku);
                } catch (Exception e) {
                    detalle.setDatosSku("SKU no disponible actualmente");
                }
            });
        }
    }
    return venta;
}

    @Transactional
    public Venta guardar(Venta venta) {

        venta.setFechaHora(LocalDateTime.now());
        if (venta.getDetalles() != null) {
            venta.getDetalles().forEach(detalle -> detalle.setVenta(venta));
        }
        return ventaRepository.save(venta);
    }

    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }


    private Venta enriquecerConCliente(Venta venta) {
        if (venta.getClienteId() != null) {
            try {
                Object cliente = webClientBuilder.build()
                        .get()
                        .uri("http://localhost:8085/clientes/" + venta.getClienteId())
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
                venta.setDatosCliente(cliente);
            } catch (Exception e) {
                venta.setDatosCliente("Cliente no disponible actualmente");
            }
        }
        return venta;
    }
}