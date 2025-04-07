package com.example.arme.service;

import com.example.arme.model.Arme;
import com.example.arme.repository.ArmeRepository;
import org.springframework.stereotype.Service;

@Service
public class ArmeService {
    private final ArmeRepository armeRepository;

    public ArmeService(ArmeRepository armeRepository) {
        this.armeRepository = armeRepository;
    }

    public Arme getArmeById(String armeId) {
        return armeRepository.findById(armeId).get();
    }
}
