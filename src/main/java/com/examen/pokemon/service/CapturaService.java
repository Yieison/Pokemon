package com.examen.pokemon.service;

import com.examen.pokemon.model.Captura;
import com.examen.pokemon.repository.CapturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CapturaService {
    
    @Autowired
    private CapturaRepository capturaRepository;

    public List<Captura> getAllCapturas() {
        return capturaRepository.findAll();
    }

    public Optional<Captura> getCapturaById(Long id) {
        return capturaRepository.findById(id);
    }

    public Captura saveCaptura(Captura captura) {
        return capturaRepository.save(captura);
    }

    public void deleteCaptura(Long id) {
        capturaRepository.deleteById(id);
    }
}
