package com.example.skins.controller;

import com.example.skins.model.Skin;
import com.example.skins.model.dto.CaisseSkinDTO;
import com.example.skins.model.dto.SkinDTO;
import com.example.skins.service.SkinImportService;
import com.example.skins.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/skins/getSkinByIdForCaisse")
    public CaisseSkinDTO getSkinById(@RequestBody String skinId){
        return skinService.getSKinByIdForCaisse(skinService.getSkinById(skinId));
    }

    @PostMapping("skins/getAllSkins")
    public List<Skin> getAllSkins(){
        return skinService.getAllSkins();
    }

    @PostMapping("skins/search")
    public List<SkinDTO> searchSkins(@RequestBody String query) {
        List<Skin> skins = skinService.findByNameContainingIgnoreCaseLimitOne(query);
        return skins.stream()
                .map(skin -> new SkinDTO(skin.getId(), skin.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping("skins/searchByName")
    public String searchSkinByName(@RequestBody String query) {
        Skin skin = skinService.findByNameContainingIgnoreCaseLimitOne(query).get(0);
        return skin.getId();
    }


    @PostMapping("skins/getHundred")
    public List<Skin> getHundred(){
        return skinService.getHundredSkin();
    }
}
