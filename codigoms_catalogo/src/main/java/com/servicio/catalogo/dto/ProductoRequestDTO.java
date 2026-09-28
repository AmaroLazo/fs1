package com.servicio.catalogo.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoRequestDTO {
    @NotBlank private String nombre;
    @NotBlank private String descripcion;
    @NotNull @DecimalMin("0.0") private BigDecimal precio;
    @NotBlank private String imagen;
    @NotBlank private String animal;
    @NotBlank private String categoria;
    @NotNull @DecimalMin("0") private Integer stock;
}
