package com.examen.pokemon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "tipo_pokemon", referencedColumnName = "id")
    private TipoPokemon tipoPokemon;

    @Column(nullable = false)
    private LocalDate fechaDescubrimiento;

    @Column(nullable = false)
    private Integer generacion;

    @Column(nullable = false, length = 100)
    private String uuid;
}
