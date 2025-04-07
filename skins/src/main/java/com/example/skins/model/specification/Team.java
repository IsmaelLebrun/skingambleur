package com.example.skins.model.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @JsonProperty("id")
    private String teamId;
    @JsonProperty("name")
    private String teamName;
}
