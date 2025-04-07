package com.example.skins.model.specification;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Crate {
    @JsonProperty("id")
    private String crateId;
    @JsonProperty("name")
    private String crateName;
    private String image;
}