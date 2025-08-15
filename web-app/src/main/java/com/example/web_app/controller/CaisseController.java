package com.example.web_app.controller;

import com.example.web_app.model.Caisse;
import com.example.web_app.model.dto.SkinDTO;
import com.example.web_app.service.CaisseService;
import org.springframework.stereotype.Controller;
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

    @PostMapping("caisse/create")
    public boolean createCaisse(@RequestBody Caisse caisse) {
        return caisseService.createCaisse(caisse);
    }

    @PostMapping("caisse/getSkinsIdsByName")
    public List<SkinDTO> getSkinsIdsByName(@RequestBody String caisseName){
        return caisseService.getSkinsByCaisseName(caisseName);
    }
}
