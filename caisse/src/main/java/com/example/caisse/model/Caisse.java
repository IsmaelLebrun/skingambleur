package com.example.caisse.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "caisse")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caisse {
    @Id
    private String id;
    private String name;
    private Double price;
    private String category;
    private List<String> skinsIds = new ArrayList<>();

    //TODO mettre image
    private String imageLink;

    public Caisse(String name, Double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
