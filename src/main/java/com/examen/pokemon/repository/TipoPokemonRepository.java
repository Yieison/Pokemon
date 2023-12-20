package com.examen.pokemon.repository;

import com.examen.pokemon.model.TipoPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPokemonRepository extends JpaRepository<TipoPokemon, Long> {
}
