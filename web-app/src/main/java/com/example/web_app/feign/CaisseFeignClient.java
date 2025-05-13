package com.example.web_app.feign;


import com.example.web_app.model.Caisse;
import com.example.web_app.model.dto.SkinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "caisse", url = "http://localhost:8010/")
public interface CaisseFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "caisse/getAll", consumes = "application/json")
    List<Caisse> getAll();

    @RequestMapping(method = RequestMethod.POST, value = "caisse/createCaisse", consumes = "application/json")
    void createCase(@RequestBody Caisse caisse);

    @RequestMapping(method = RequestMethod.POST, value = "caisse/getByName", consumes = "application/json")
    Caisse getCaisseByName(String caisseName);

    @RequestMapping(method = RequestMethod.POST, value = "caisse/getSkinsByName", consumes = "application/json")
    List<SkinDTO> getSkinsByCaisseName(String caisseName);
}
