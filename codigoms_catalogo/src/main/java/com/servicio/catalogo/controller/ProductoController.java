package com.servicio.catalogo.controller;

import com.servicio.catalogo.dto.ProductoRequestDTO;
import com.servicio.catalogo.dto.ProductoResponseDTO;
import com.servicio.catalogo.model.Producto;
import com.servicio.catalogo.repository.ProductoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoRepository repository;

    @GetMapping
    public List<ProductoResponseDTO> todos(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String animal,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean disponible) {

        List<Producto> productos;
        if (q != null && !q.isBlank()) {
            productos = repository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(q, q);
        } else if (animal != null && !animal.isBlank() && categoria != null && !categoria.isBlank()) {
            productos = repository.findByAnimalContainingIgnoreCaseAndCategoriaIgnoreCase(animal, categoria);
        } else if (animal != null && !animal.isBlank()) {
            productos = repository.findByAnimalContainingIgnoreCase(animal);
        } else if (categoria != null && !categoria.isBlank()) {
            productos = repository.findByCategoriaIgnoreCase(categoria);
        } else if (Boolean.TRUE.equals(disponible)) {
            productos = repository.findByDisponibleTrue();
        } else {
            productos = repository.findAll();
        }
        return productos.stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> porId(@PathVariable Long id) {
        return repository.findById(id)
                .map(p -> ResponseEntity.ok(toResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto) {
        Producto producto = new Producto();
        aplicar(dto, producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(repository.save(producto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ProductoRequestDTO dto) {
        return repository.findById(id).map(p -> {
            aplicar(dto, p);
            return ResponseEntity.ok(toResponse(repository.save(p)));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private void aplicar(ProductoRequestDTO dto, Producto p) {
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setImagen(dto.getImagen());
        p.setAnimal(dto.getAnimal());
        p.setCategoria(dto.getCategoria());
        p.setStock(dto.getStock());
        p.setDisponible(dto.getStock() > 0);
    }

    private ProductoResponseDTO toResponse(Producto p) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setImagen(p.getImagen());
        dto.setAnimal(p.getAnimal());
        dto.setCategoria(p.getCategoria());
        dto.setStock(p.getStock());
        dto.setDisponible(p.isDisponible());
        return dto;
    }
}
