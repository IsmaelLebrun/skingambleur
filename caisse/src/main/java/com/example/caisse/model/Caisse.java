package com.example.caisse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private String category;

    @Column(nullable = true)
    @OneToMany(mappedBy = "caisse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaisseSkins> caisseSkins = new ArrayList<>();

    public Caisse(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
