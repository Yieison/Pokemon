package com.examen.pokemon.service;

import com.examen.pokemon.model.Entrenador;
import com.examen.pokemon.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {
    
    @Autowired
    private EntrenadorRepository entrenadorRepository;

    public List<Entrenador> getAllEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public Optional<Entrenador> getEntrenadorById(Long id) {
        return entrenadorRepository.findById(id);
    }

    public Entrenador saveEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public void deleteEntrenador(Long id) {
        entrenadorRepository.deleteById(id);
    }
}
