package com.examen.pokemon.service;

import com.examen.pokemon.model.TipoPokemon;
import com.examen.pokemon.repository.TipoPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TipoPokemonService {
    
    @Autowired
    private TipoPokemonRepository tipoPokemonRepository;

    public List<TipoPokemon> getAllTipoPokemons() {
        return tipoPokemonRepository.findAll();
    }

    public Optional<TipoPokemon> getTipoPokemonById(Long id) {
        return tipoPokemonRepository.findById(id);
    }

    public TipoPokemon saveTipoPokemon(TipoPokemon tipoPokemon) {
        return tipoPokemonRepository.save(tipoPokemon);
    }

    public void deleteTipoPokemon(Long id) {
        tipoPokemonRepository.deleteById(id);
    }
}
