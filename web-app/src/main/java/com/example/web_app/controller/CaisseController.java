package com.example.web_app.controller;

import com.example.web_app.model.Caisse;
import com.example.web_app.service.CaisseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaisseController {

    private CaisseService caisseService;

    public CaisseController(CaisseService caisseService) {
        this.caisseService = caisseService;
    }

    @PostMapping("caisse/create")
    public boolean createCaisse(@RequestBody Caisse caisse) {
        return caisseService.createCaisse(caisse);
    }
}
