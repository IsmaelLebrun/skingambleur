package com.example.web_app.feign;


import com.example.web_app.model.Caisse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "caisse", url = "http://localhost:8010/")
public interface CaisseFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "caisse/getAll", consumes = "application/json")
    public List<Caisse> getAll();
}
