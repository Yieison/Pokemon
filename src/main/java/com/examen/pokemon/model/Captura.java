package com.examen.pokemon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "captura")
public class Captura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    private Pokemon pokemon;

    @ManyToOne
    @JoinColumn(name = "entrenador_id", referencedColumnName = "id")
    private Entrenador entrenador;
}
