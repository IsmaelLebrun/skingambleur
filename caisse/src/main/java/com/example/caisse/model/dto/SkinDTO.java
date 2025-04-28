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
}
