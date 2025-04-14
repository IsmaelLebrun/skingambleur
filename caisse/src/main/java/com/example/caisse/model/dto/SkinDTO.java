package com.example.caisse.model.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
public class SkinDTO {
    @Id
    private String id;
    private String name;
    private String description;

}
