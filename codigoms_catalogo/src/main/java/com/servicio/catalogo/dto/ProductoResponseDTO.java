package com.servicio.catalogo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String imagen;
    private String animal;
    private String categoria;
    private Integer stock;
    private boolean disponible;
}
