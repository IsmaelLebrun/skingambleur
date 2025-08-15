package com.example.caisse.service;

import com.example.caisse.feign.SkinFeignClient;
import com.example.caisse.model.Caisse;
import com.example.caisse.model.dto.SkinDTO;
import com.example.caisse.repository.CaisseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaisseService {

    private final CaisseRepository caisseRepository;
    private final SkinFeignClient skinFeignClient;

    public CaisseService(CaisseRepository caisseRepository, SkinFeignClient skinFeignClient) {
        this.caisseRepository = caisseRepository;
        this.skinFeignClient = skinFeignClient;
    }
    public List<Caisse> getAll(){
        return caisseRepository.findAll();
    }

    public void create(Caisse caisse) throws RuntimeException {
        Caisse existingCaisse = caisseRepository.findByName(caisse.getName());
        if(existingCaisse != null){
            throw new RuntimeException("Caisse already exist");
        }
        caisseRepository.save(caisse);
    }

    public Caisse getCaisseByName(String caisseName) {
        try{
            return caisseRepository.findByName(caisseName);
        }catch (Exception e){
            throw new IllegalArgumentException("Caisse non trouvée : " + caisseName, e);
        }
    }

    public List<SkinDTO> getSkinsByCaisseName(String caisseName) {
        Caisse caisse = getCaisseByName(caisseName);
        if(caisse == null){
            throw new IllegalArgumentException("Caisse non trouvée : " + caisseName);
        }
        List<SkinDTO> skins = new ArrayList<>();
        caisse.getSkinsIds().forEach(id -> {
            skins.add(skinFeignClient.getSkinById(id));
        });
        return skins;
    }
}
