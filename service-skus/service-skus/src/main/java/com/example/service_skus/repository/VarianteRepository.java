package com.example.service_skus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.service_skus.model.VarianteSku;
import java.util.List;

@Repository
public interface VarianteRepository extends JpaRepository<VarianteSku,Long>{

    List<VarianteSku> findByTalla(String talla);

    List<VarianteSku> findByIdProducto(Long idProducto);

    List<VarianteSku> findByColor(String color);

}
