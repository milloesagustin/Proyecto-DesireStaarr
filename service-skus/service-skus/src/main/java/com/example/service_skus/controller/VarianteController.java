package com.example.service_skus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service_skus.model.VarianteSku;
import com.example.service_skus.service.VarianteService;

@RestController
@RequestMapping("/skus")
public class VarianteController {
    @Autowired
    private VarianteService varianteService;

    @GetMapping
    public List<VarianteSku> listar(){
        return varianteService.listarTodas();
    }

    @GetMapping("/{sku}")
    public ResponseEntity<VarianteSku> obtener(@PathVariable String sku){
        return varianteService.buscarPorSku(sku)
                              .map(ResponseEntity::ok)
                              .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/talla/{talla}")
    public List<VarianteSku> porTalla(@PathVariable String talla){
        return varianteService.buscarPorTalla(talla);
    }

    @PostMapping
    public ResponseEntity<VarianteSku> crear(@RequestBody VarianteSku variante){
        return ResponseEntity.ok(varianteService.guardar(variante));
    }


    @PutMapping("/{sku}")
    public VarianteSku actualizar(@PathVariable String sku, @RequestBody VarianteSku variante){
        variante.setIdSku(sku);
        return varianteService.guardar(variante);
    }


    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> eliminar(@PathVariable String sku){
        varianteService.eliminar(sku);
        return ResponseEntity.noContent().build(); //esto devuelve un estado 204
    }


    
}
