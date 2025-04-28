package com.example.web_app.feign;

import com.example.web_app.model.dto.SkinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "skins", url="http://localhost:8000/")
public interface SkinFeignClient {

    @RequestMapping(method = RequestMethod.POST, value = "skins/getAllSkins", consumes = "application/json")
    List<SkinDTO> getAll();

    @RequestMapping(method = RequestMethod.POST, value = "skins/search", consumes = "application/json")
    List<SkinDTO> findByNameContainingIgnoreCase(@RequestBody String query);

    @RequestMapping(method = RequestMethod.POST, value = "skins/getHundred", consumes = "application/json")
    List<SkinDTO> getHundredFirst();
}
