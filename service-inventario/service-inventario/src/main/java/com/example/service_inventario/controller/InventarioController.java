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

    @GetMapping("/sku/{IdSku}")
    public ResponseEntity<Inventario> obtenerPorSku(@PathVariable Long IdSku) {
        return service.buscarPorSku(IdSku)
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

    @PutMapping("/actualizar/{IdSku}/{cantidad}")
    public ResponseEntity<?> actualizar(@PathVariable Long IdSku, @PathVariable int cantidad) {
        try {
            return ResponseEntity.ok(service.actualizarStock(IdSku, cantidad));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    

}
