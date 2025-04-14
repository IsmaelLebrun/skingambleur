package com.example.caisse.model;

import com.example.caisse.model.dto.SkinDTO;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

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
    private Long price;

    @CollectionTable(name = "caisses_skins", joinColumns = @JoinColumn(name = "caisse_id"))
    @ManyToMany
    private List<SkinDTO> skins;
//    private List<String> bestLastDrop;
}
