package com.example.caisse.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaisseSkin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caisse_id")
    private Caisse caisse;

    private String skinId; // Stocke uniquement l'ID du skin récupéré via Feign

    // Getters and setters
}
