package com.example.arme.controller;

import com.example.arme.model.Arme;
import com.example.arme.service.ArmeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/armes/")
public class ArmeController {

    private final ArmeService armeService;

    public ArmeController(ArmeService armeService) {
        this.armeService = armeService;
    }

    @PostMapping("getArmeById")
    public Arme getArmeById(@RequestBody String armeId) {
        return armeService.getArmeById(armeId);
    }


}
