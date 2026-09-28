package com.servicio.catalogo.repository;

import com.servicio.catalogo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByAnimalContainingIgnoreCase(String animal);
    List<Producto> findByCategoriaIgnoreCase(String categoria);
    List<Producto> findByAnimalContainingIgnoreCaseAndCategoriaIgnoreCase(String animal, String categoria);
    List<Producto> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);
    List<Producto> findByDisponibleTrue();
}
