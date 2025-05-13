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
    private List<String> skins;

    // TODO: add image
    private String image;
    //

    private List<SkinDTO> skinDTOS;

    public Caisse(String name, Double price, List<String> skins) {
        this.name = name;
        this.price = price;
        this.skins = skins;
    }

    public Caisse(List<SkinDTO> skinDTOS, String category, Double price, String name) {
        this.skinDTOS = skinDTOS;
        this.category = category;
        this.price = price;
        this.name = name;
    }
}
