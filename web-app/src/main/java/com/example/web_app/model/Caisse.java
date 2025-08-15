package com.example.web_app.model;

import com.example.web_app.model.dto.SkinDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Caisse {
    private String name;
    private Double price;
    private String category;
    private List<String> skinsIds;
    private List<SkinDTO> skins;

    //TODO images
    private String imageLink;


    public static Caisse createCaisseWithSkins(String name, Double price, String category, List<SkinDTO> skins) {
        Caisse caisse = new Caisse();
        caisse.setName(name);
        caisse.setPrice(price);
        caisse.setCategory(category);
        caisse.setSkins(skins);
        return caisse;
    }

    public Caisse(String name, Double price, String category, List<String> skinsIds) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.skinsIds = skinsIds;
    }

    public Caisse(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
