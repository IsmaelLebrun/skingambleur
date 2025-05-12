package com.example.web_app.model;

import com.example.web_app.model.dto.SkinDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caisse {
    private Integer id;
    private String name;
    private Long price;
    // format de reception des données des skins depuis la création de la caisse
    private List<SkinsData> skin;
    // format après formattage de skins;
    private List<SkinDTO> skins_dto;

    public Caisse(Integer id, String name, Long price, List<SkinsData> skins) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.skin = skins;
    }
}
