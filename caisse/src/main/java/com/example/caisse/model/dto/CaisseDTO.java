package com.example.caisse.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CaisseDTO {
    private String name;
    private Double price;
    private String category;
    private List<String> skins;

    public CaisseDTO(String name, Double price, List<String> skins) {
        this.name = name;
        this.price = price;
        this.skins = skins;
    }
}
