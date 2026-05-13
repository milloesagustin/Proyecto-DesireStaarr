package com.example.service_inventario.repository;
import com.example.service_inventario.model.Inventario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    // usamos Optional para un solo resultado
    Optional<Inventario> findByIdSku(Long idSku);
    
    List<Inventario> findByCantidadActualLessThan(int cantidad);
    List<Inventario> findByCantidadActualGreaterThan(int cantidad);

}
