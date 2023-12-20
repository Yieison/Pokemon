package com.examen.pokemon.controller;

import com.examen.pokemon.dto.EntrenadorDTO;
import com.examen.pokemon.model.Entrenador;
import com.examen.pokemon.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entrenador")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @GetMapping
    public ResponseEntity<List<EntrenadorDTO>> getAllEntrenadores() {
        List<EntrenadorDTO> entrenadores = entrenadorService.getAllEntrenadores().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(entrenadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrenadorDTO> getEntrenadorById(@PathVariable Long id) {
        return entrenadorService.getEntrenadorById(id)
                .map(this::convertToDTO) // Assuming convertToDTO is a method in this controller class
                .map(ResponseEntity::ok) // Wrap the DTO in a ResponseEntity if present
                .orElse(ResponseEntity.notFound().build()); // Or respond with a 404 Not Found status
    }


    @PostMapping
    public ResponseEntity<EntrenadorDTO> createEntrenador(@RequestBody EntrenadorDTO entrenadorDTO) {
        Entrenador entrenador = convertToEntity(entrenadorDTO);
        Entrenador savedEntrenador = entrenadorService.saveEntrenador(entrenador);
        EntrenadorDTO savedEntrenadorDTO = convertToDTO(savedEntrenador);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntrenadorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrenadorDTO> updateEntrenador(@PathVariable Long id, @RequestBody EntrenadorDTO entrenadorDTO) {
        Entrenador existingEntrenador = entrenadorService.getEntrenadorById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrenador not found"));
        // Update the existing entity with DTO data
        Entrenador updatedEntrenador = entrenadorService.saveEntrenador(existingEntrenador);
        EntrenadorDTO updatedEntrenadorDTO = convertToDTO(updatedEntrenador);
        return ResponseEntity.ok(updatedEntrenadorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntrenador(@PathVariable Long id) {
        entrenadorService.deleteEntrenador(id);
        return ResponseEntity.ok().build();
    }
    
    private EntrenadorDTO convertToDTO(Entrenador entrenador) {
        EntrenadorDTO dto = new EntrenadorDTO();
        dto.setId(entrenador.getId());
        dto.setNombre(entrenador.getNombre());
        dto.setApellido(entrenador.getApellido());
        dto.setFechaNacimiento(entrenador.getFechaNacimiento());
        dto.setFechaVinculacion(entrenador.getFechaVinculacion());
        dto.setEmail(entrenador.getEmail());
        return dto;
    }
    
    private Entrenador convertToEntity(EntrenadorDTO dto) {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(dto.getId());
        entrenador.setNombre(dto.getNombre());
        entrenador.setApellido(dto.getApellido());
        entrenador.setFechaNacimiento(dto.getFechaNacimiento());
        entrenador.setFechaVinculacion(dto.getFechaVinculacion());
        entrenador.setEmail(dto.getEmail());
        return entrenador;
    }

}
