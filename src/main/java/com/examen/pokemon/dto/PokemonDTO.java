package com.examen.pokemon.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PokemonDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long tipoPokemonId;
    private LocalDate fechaDescubrimiento;
    private Integer generacion;
}
