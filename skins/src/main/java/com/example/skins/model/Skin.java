package com.example.skins.model;

import com.example.skins.model.dto.CaisseDTO;
import com.example.skins.model.specification.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Getter
public class Skin {
    @Id
    private String id;
    private String name;
    @Column(length = 5000)
    private String description;
    @Embedded
    private Weapon weapon;
    @Embedded
    private Category category;
    @Embedded
    private Pattern pattern;
    private double min_float;
    private double max_float;
    private Rarity rarity;
    private boolean stattrak;
    private boolean souvenir;
    private String paint_index;
    @ElementCollection
    private List<Wear> wears;
    @ElementCollection
    @JsonIgnore
    private List<String> collections = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "skin_crates", joinColumns = @JoinColumn(name = "skin_id"))
    private List<Crate> Crates;
    @ElementCollection
    @CollectionTable(name = "skin_special_notes", joinColumns = @JoinColumn(name = "skin_id"))
    @Column(length = 5000)
    private List<Note> special_notes;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String phase;
    @Embedded
    private Team team;
    private boolean legacy_model;
    private String image;
}
