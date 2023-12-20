package com.examen.pokemon.controller;

import com.examen.pokemon.dto.CapturaDTO;
import com.examen.pokemon.model.Captura;
import com.examen.pokemon.service.CapturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/captura")
public class CapturaController {

    @Autowired
    private CapturaService capturaService;

    @GetMapping
    public ResponseEntity<List<CapturaDTO>> getAllCapturas() {
        List<CapturaDTO> capturas = capturaService.getAllCapturas().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(capturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CapturaDTO> getCapturaById(@PathVariable Long id) {
        return capturaService.getCapturaById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CapturaDTO> createCaptura(@RequestBody CapturaDTO capturaDTO) {
        Captura captura = convertToEntity(capturaDTO);
        Captura savedCaptura = capturaService.saveCaptura(captura);
        CapturaDTO savedCapturaDTO = convertToDTO(savedCaptura);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCapturaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CapturaDTO> updateCaptura(@PathVariable Long id, @RequestBody CapturaDTO capturaDTO) {
        Captura existingCaptura = capturaService.getCapturaById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Captura not found"));
        updateEntity(existingCaptura, capturaDTO);
        Captura updatedCaptura = capturaService.saveCaptura(existingCaptura);
        CapturaDTO updatedCapturaDTO = convertToDTO(updatedCaptura);
        return ResponseEntity.ok(updatedCapturaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCaptura(@PathVariable Long id) {
        capturaService.deleteCaptura(id);
        return ResponseEntity.ok().build();
    }

    private CapturaDTO convertToDTO(Captura captura) {
        CapturaDTO dto = new CapturaDTO();
        dto.setId(captura.getId());
        dto.setPokemonId(captura.getPokemon().getId());
        dto.setEntrenadorId(captura.getEntrenador().getId());
        return dto;
    }

    private Captura convertToEntity(CapturaDTO dto) {
        Captura captura = new Captura();
        // You should fetch and set Pokemon and Entrenador entities here
        // Example: captura.setPokemon(pokemonService.getPokemonById(dto.getPokemonId()).orElse(null));
        // ...
        return captura;
    }

    private void updateEntity(Captura captura, CapturaDTO dto) {
        // Update logic
    }
}
