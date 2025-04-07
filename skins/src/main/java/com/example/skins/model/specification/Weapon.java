package com.example.skins.model.specification;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public class Weapon {
    @JsonProperty("id")
    private String skinName;
    private Integer weapon_id;
    @JsonProperty("name")
    private String weaponName;
}
