package com.example.web_app.controller;

import com.example.web_app.model.Caisse;
import com.example.web_app.model.dto.SkinDTO;
import com.example.web_app.service.SkinService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkinsController {

    private final SkinService skinService;

    public SkinsController(SkinService skinService) {
        this.skinService = skinService;
    }

    @GetMapping("/skins/search")
    public List<SkinDTO> searchSkinsByPrice(@RequestParam Double price, @RequestParam String query, @RequestParam Double multiplier) {
        return skinService.skinSearch(query, price, multiplier);
    }
}

