package com.example.caisse.controller;

import com.example.caisse.feign.SkinFeignClient;
import com.example.caisse.model.Caisse;
import com.example.caisse.model.dto.SkinDTO;
import com.example.caisse.service.CaisseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CaisseController {

    private final SkinFeignClient skinFeignClient;
    private CaisseService caisseService;

    public CaisseController(CaisseService caisseService, SkinFeignClient skinFeignClient) {
        this.caisseService = caisseService;
        this.skinFeignClient = skinFeignClient;
    }

    @PostMapping("/caisse/createCaisse")
    public void createCaisse(@Valid @RequestBody Caisse caisse) {
        caisseService.create(caisse);
    }

    @PostMapping("/caisse/getAll")
    public List<Caisse> getAllCase(){
        return caisseService.getAll();
    }

    @PostMapping("/caisse/getByName")
    public Caisse getByName(@RequestBody String caisseName){
        return caisseService.getCaisseByName(caisseName);
    }

    @PostMapping("caisse/getSkinsByName")
    public List<SkinDTO> getSkinsByCaisseName(@RequestBody String caisseName){
        return caisseService.getSkinsByCaisseName(caisseName);
    }
}
