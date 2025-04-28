package com.example.caisse.controller;

import com.example.caisse.model.Caisse;
import com.example.caisse.service.CaisseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CaisseController {

    private CaisseService caisseService;

    public CaisseController(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @PostMapping("caisse/createCaisse")
    public void createCaisse(@Valid @ModelAttribute @RequestBody Caisse caisse) {
        caisseService.create(caisse);
    }

    @PostMapping("/caisse/getAll")
    public List<Caisse> getAllCase(){
        return caisseService.getAll();
    }
}
