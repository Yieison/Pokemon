package com.examen.pokemon.service;

import com.examen.pokemon.model.Pueblo;
import com.examen.pokemon.repository.PuebloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PuebloService {
    
    @Autowired
    private PuebloRepository puebloRepository;

    public List<Pueblo> getAllPueblos() {
        return puebloRepository.findAll();
    }

    public Optional<Pueblo> getPuebloById(Long id) {
        return puebloRepository.findById(id);
    }

    public Pueblo savePueblo(Pueblo pueblo) {
        return puebloRepository.save(pueblo);
    }

    public void deletePueblo(Long id) {
        puebloRepository.deleteById(id);
    }
}
