package com.example.service_inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long IdSku; 
    private int cantidadActual;
    private int stockMinimo;

    @Transient // Para mostrar los datos del SKU sin guardarlos en esta DB
    private Object sku;
}
