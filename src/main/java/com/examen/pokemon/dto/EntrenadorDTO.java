package com.examen.pokemon.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EntrenadorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private LocalDate fechaVinculacion;
    private String email;
}
