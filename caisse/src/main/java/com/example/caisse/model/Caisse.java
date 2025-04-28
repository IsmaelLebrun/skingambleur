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

    public Caisse(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long price;

    @OneToMany(mappedBy = "caisse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaisseSkin> caisseSkins = new ArrayList<>();
//    private List<String> bestLastDrop;
}
