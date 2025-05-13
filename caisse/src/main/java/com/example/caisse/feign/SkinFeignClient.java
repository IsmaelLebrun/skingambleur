package com.example.caisse.feign;

import com.example.caisse.model.dto.SkinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "skins", url = "http://localhost:8000/")
public interface SkinFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "skins/getSkinByIdForCaisse", consumes = "application/json")
    SkinDTO getSkinById(String id);

    @RequestMapping(method = RequestMethod.POST, value = "skins/searchByName", consumes = "application/json")
    String skinSearch(String query);
}
