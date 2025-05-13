package com.example.skins.model.dto;

import com.example.skins.model.specification.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SkinDTO {
    private String id;
    private String name;
    private Integer multiplier;
    private String state;
    private Double suggested_price;
    public SkinDTO(String name, Integer multiplier, String state) {
        this.id = id;
        this.name = name;
        this.multiplier = multiplier;
        this.state = state;
    }

    public SkinDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}