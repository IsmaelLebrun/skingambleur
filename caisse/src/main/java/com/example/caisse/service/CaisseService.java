package com.example.caisse.service;

import com.example.caisse.feign.SkinFeignClient;
import com.example.caisse.model.Caisse;
import com.example.caisse.model.CaisseSkins;
import com.example.caisse.model.dto.CaisseDTO;
import com.example.caisse.model.dto.SkinDTO;
import com.example.caisse.repository.CaisseRepository;
import jakarta.validation.Valid;
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

    public void create(CaisseDTO caisseDTO) {
        Caisse caisse = formatCaisse(caisseDTO);
        caisseRepository.save(caisse);
    }

    private Caisse formatCaisse(CaisseDTO caisseDTO) {
        Caisse caisse = new Caisse();
        caisse.setName(caisseDTO.getName());
        caisse.setPrice(caisseDTO.getPrice());
        caisse.setCategory(caisseDTO.getCategory());

        List<CaisseSkins> caisseSkinsList = caisseDTO.getSkins().stream()
                .map(skinName -> {
                    String skinNameFormat = parseSkinData(skinName);
                    String skinId = findSkinIdByName(skinNameFormat);
                    if (skinId == null) {
                        throw new IllegalArgumentException("Skin not found: " + skinName);
                    }
                    return new CaisseSkins(caisse, skinId);
                })
                .collect(Collectors.toList());

        caisse.setCaisseSkins(caisseSkinsList);
        return caisse;
    }


    private String findSkinIdByName(String skinName) {
        return skinFeignClient.skinSearch(skinName);
    }


    private String parseSkinData(String skinData) {
        System.out.println(skinData);
        // Vérifier si la chaîne est null ou vide
        if (skinData == null || skinData.isEmpty()) {
            throw new IllegalArgumentException("Le skinData ne peut pas être null ou vide.");
        }

        try {
            int lastSpaceXIndex = skinData.lastIndexOf(" x");
            if (lastSpaceXIndex == -1) {
                throw new IllegalArgumentException("Le format de la chaîne est incorrect (multiplicateur manquant) : " + skinData);
            }

            // Extraire la partie sans le multiplicateur
            String nameAndState = skinData.substring(0, lastSpaceXIndex);

            // Identifier la position de la dernière parenthèse ouvrante '('
            int lastParenIndex = nameAndState.lastIndexOf('(');
            if (lastParenIndex == -1) {
                throw new IllegalArgumentException("Le format de la chaîne est incorrect : " + skinData);
            }

            // Extraire la partie avant la parenthèse
            return nameAndState.substring(0, lastParenIndex).trim(); // Exemple : "MAC-10 | Amber Fade"
        } catch (Exception e) {
            // Gérer les exceptions inattendues
            throw new IllegalArgumentException("Erreur lors du parsing de la chaîne : " + skinData, e);
        }
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
        caisse.getCaisseSkins().forEach(caisseSkins -> {
            if(caisseSkins.getCaisse() == caisse){
                skins.add(skinFeignClient.getSkinById(caisseSkins.getSkinId()));
            }
        });
        return skins;
    }
}
