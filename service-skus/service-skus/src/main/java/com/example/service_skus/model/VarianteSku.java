package com.example.service_skus.model;

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

public class VarianteSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSku; //PK tipo string ej: POL-V-ROJO-M

    private Long idProducto; //FK
    private String talla; //s,m,l,xl
    private String color; //rojo,azul,etc
    private String codigoBarras;


    @Transient
    private Object producto;

    


}
