package com.examen.pokemon.controller;

import com.examen.pokemon.dto.PokemonDTO;
import com.examen.pokemon.model.Pokemon;
import com.examen.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> getAllPokemon() {
        List<PokemonDTO> pokemons = pokemonService.getAllPokemons().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable Long id) {
        PokemonDTO pokemonDTO = pokemonService.getPokemonById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon not found"));
        return ResponseEntity.ok(pokemonDTO);
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO) {
        Pokemon pokemon = convertToEntity(pokemonDTO);
        Pokemon savedPokemon = pokemonService.savePokemon(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedPokemon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(@PathVariable Long id, @RequestBody PokemonDTO pokemonDTO) {
        Pokemon existingPokemon = pokemonService.getPokemonById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokemon not found"));
        updateEntity(existingPokemon, pokemonDTO);
        Pokemon updatedPokemon = pokemonService.savePokemon(existingPokemon);
        return ResponseEntity.ok(convertToDTO(updatedPokemon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.ok().build();
    }

    private PokemonDTO convertToDTO(Pokemon pokemon) {
        PokemonDTO dto = new PokemonDTO();
        dto.setId(pokemon.getId());
        dto.setNombre(pokemon.getNombre());
        dto.setDescripcion(pokemon.getDescripcion());
        dto.setTipoPokemonId(pokemon.getTipoPokemon().getId()); // Assuming Pokemon has a TipoPokemon field
        dto.setFechaDescubrimiento(pokemon.getFechaDescubrimiento());
        dto.setGeneracion(pokemon.getGeneracion());
        // ... map additional fields
        return dto;
    }

    private Pokemon convertToEntity(PokemonDTO dto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(dto.getId()); // Usually null for POST
        pokemon.setNombre(dto.getNombre());
        pokemon.setDescripcion(dto.getDescripcion());
        // You'll need to fetch and set the TipoPokemon entity related to the id
        pokemon.setFechaDescubrimiento(dto.getFechaDescubrimiento());
        pokemon.setGeneracion(dto.getGeneracion());
        // ... map additional fields
        return pokemon;
    }

    private void updateEntity(Pokemon pokemon, PokemonDTO dto) {
        pokemon.setNombre(dto.getNombre());
        pokemon.setDescripcion(dto.getDescripcion());
        // ... update additional fields as necessary
    }

}
