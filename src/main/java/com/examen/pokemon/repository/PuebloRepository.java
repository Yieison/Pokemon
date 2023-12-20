package com.examen.pokemon.repository;

import com.examen.pokemon.model.Pueblo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuebloRepository extends JpaRepository<Pueblo, Long> {
}
