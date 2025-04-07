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
public class Rarity {
    @JsonProperty("id")
    private String rarityId;
    @JsonProperty("name")
    private String rarirtyName;
    private String color;
}
