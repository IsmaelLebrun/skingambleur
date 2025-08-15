package com.example.caisse.repository;

import com.example.caisse.model.Caisse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaisseRepository extends MongoRepository<Caisse, String> {
    Caisse findByName(String caisseName);
}
