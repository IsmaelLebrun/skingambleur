package com.example.skins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Arme {
    public Arme(String id, String skinId, double floatValue, String wear, double price, boolean isTradeable, LocalDateTime tradeAvailableAt, String ownerId) {
        this.id = id;
        this.skinId = skinId;
        this.floatValue = floatValue;
        this.wear = wear;
        this.price = price;
        this.isTradeable = isTradeable;
        this.tradeAvailableAt = tradeAvailableAt;
        this.ownerId = ownerId;
    }

    @Id
    private String id;
    private String skinId;
    private double floatValue;
    private String wear;

    private double price;

    private boolean isTradeable;
    private LocalDateTime tradeAvailableAt;

    private String dopplerPhase;
    private String ownerId;
}
