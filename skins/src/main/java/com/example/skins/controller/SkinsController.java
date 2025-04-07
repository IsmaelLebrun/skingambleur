package com.example.skins.controller;

import com.example.skins.model.Skin;
import com.example.skins.service.SkinImportService;
import com.example.skins.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SkinsController {
//    @Autowired
//    private SkinImportService skinImportService;

    private SkinService skinService;

    public SkinsController(SkinService skinService) {
        this.skinService = skinService;
    }

    //    @GetMapping("/import")
//    public String importSkins() throws IOException {
//        skinImportService.importSkins();
//        return "Importation des skins réussie !";
//    }

    @PostMapping("/skins/getSkinById")
    public Skin getSkinById(@RequestBody String skinId){
        return skinService.getSkinById(skinId);
    }
}
