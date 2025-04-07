package com.example.arme.repository;

import com.example.arme.model.Arme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArmeRepository extends MongoRepository<Arme, String> {
}
