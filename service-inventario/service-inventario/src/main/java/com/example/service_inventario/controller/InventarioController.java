package com.example.service_inventario.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.service_inventario.model.Inventario;
import com.example.service_inventario.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService service;

    @GetMapping
    public List<Inventario> listar() {
        return service.listarTodo();
    }

    @GetMapping("/sku/{idSku}")
    public ResponseEntity<Inventario> obtenerPorSku(@PathVariable Long idSku) {
        return service.buscarPorSku(idSku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Inventario inventario) {
        try {
            return ResponseEntity.ok(service.crearEntrada(inventario));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/actualizar/{idSku}/{cantidad}")
    public ResponseEntity<?> actualizar(@PathVariable Long idSku, @PathVariable int cantidad) {
        try {
            return ResponseEntity.ok(service.actualizarStock(idSku, cantidad));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/alertas")
    public List<Inventario> alertasReposicion() {
        return service.alertasReposicion();
    }

    @PutMapping("/sumar/{idSku}/{cantidad}")
    public ResponseEntity<?> sumarStock(@PathVariable Long idSku, @PathVariable int cantidad) {
        try {
            return ResponseEntity.ok(service.sumarStock(idSku, cantidad));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/descontar/{idSku}/{cantidad}")
    public ResponseEntity<?> descontarStock(@PathVariable Long idSku, @PathVariable int cantidad) {
        try {
            return ResponseEntity.ok(service.descontarStock(idSku, cantidad));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/existencias")
    public List<Inventario> existencias() {
        return service.stockPorSku();
    }
    
}

