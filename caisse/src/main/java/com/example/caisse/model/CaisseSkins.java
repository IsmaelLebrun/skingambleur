package com.example.caisse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CaisseSkins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "caisse_id", nullable = false)
    private Caisse caisse;

    @Column(name = "skin_id", nullable = false)
    private String skinId;

    public CaisseSkins() {}

    public CaisseSkins(Caisse caisse, String skinId) {
        this.caisse = caisse;
        this.skinId = skinId;
    }

    // Getters et setters
}
