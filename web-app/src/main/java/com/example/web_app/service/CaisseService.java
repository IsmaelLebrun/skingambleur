package com.example.web_app.service;

import com.example.web_app.feign.CaisseFeignClient;
import com.example.web_app.model.Caisse;
import com.example.web_app.model.dto.SkinDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaisseService {


    private final CaisseFeignClient caisseFeignClient;

    public CaisseService(CaisseFeignClient caisseFeignClient) {
        this.caisseFeignClient = caisseFeignClient;
    }

    public boolean createCaisse(Caisse caisse){
        caisseFeignClient.createCase(caisse);
        return true;
    }

    public List<Caisse> getAllCaisses(){
        return caisseFeignClient.getAll();
    }

    public Caisse getCaisseByName(String caisseName) {
        return caisseFeignClient.getCaisseByName(caisseName);
    }

    public List<SkinDTO> getSkinsByCaisseName(String caisseName) {
        return caisseFeignClient.getSkinsByCaisseName(caisseName);
    }
}