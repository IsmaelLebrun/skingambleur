package com.example.caisse.repository;

import com.example.caisse.model.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaisseRepository extends JpaRepository<Caisse, Integer> {
    Caisse findByName(String caisseName);
}
