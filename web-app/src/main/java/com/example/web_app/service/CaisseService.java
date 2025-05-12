package com.example.web_app.service;

import com.example.web_app.model.Caisse;
import org.springframework.stereotype.Service;

@Service
public class CaisseService {

    public boolean createCaisse(Caisse caisse){
        Caisse caisseAT = formatSkinData(caisse);
        return true;
    }

    private Caisse formatSkinData(Caisse caisse){
        Caisse caisseAT = new Caisse();
        caisseAT.setName(caisse.getName());
        caisseAT.setPrice(caisse.getPrice());
        caisseAT.setId(caisse.getId());
        
        return caisseAT;
    }
}