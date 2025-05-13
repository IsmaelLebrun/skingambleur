package com.example.caisse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SkinDTO {
    private String id;
    private String name;
    private String description;
    private Integer multiplier;
    private String state;
    private Double suggested_price;
    public SkinDTO(String name, String description, Integer multiplier, String state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
        this.state = state;
    }
    public SkinDTO(String name, String state, Integer multiplier, Double suggested_price) {
        this.name = name;
        this.multiplier = multiplier;
        this.state = state;
        this.suggested_price = suggested_price;
    }
}
