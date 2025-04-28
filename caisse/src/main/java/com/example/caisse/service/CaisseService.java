package com.example.caisse.service;

import com.example.caisse.model.Caisse;
import com.example.caisse.repository.CaisseRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaisseService {

    private final CaisseRepository caisseRepository;

    public CaisseService(CaisseRepository caisseRepository) {
        this.caisseRepository = caisseRepository;
    }

    public List<Caisse> getAll(){
        return caisseRepository.findAll();
    }

    public void create(@Valid Caisse caisse) {







        caisseRepository.save(caisse);
    }
}
