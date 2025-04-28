package com.example.caisse.feign;

import com.example.caisse.model.dto.SkinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "skins", url = "http://localhost:8000/")
public interface SkinFeignClient {

    @GetMapping("/skins/{id}")
    SkinDTO getSkinById(@PathVariable("id") String id);

    @GetMapping("/skins")
    List<SkinDTO> getAllSkins();
}
