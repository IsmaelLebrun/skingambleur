package com.example.skins.model.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
public class CaisseDTO {
    private Integer id;
    private String name;
    private Long price;

    // Getters and setters
}