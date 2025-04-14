package com.example.skins.model.dto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
public class CaisseDTO {
    @Id
    private Integer id;
    private String name;
    private Long price;

    // Getters and setters
}