package com.example.user.model;

import com.example.userM.model.Arme;
import com.mongodb.lang.Nullable;
import jakarta.persistence.Id;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Data
@Getter
@Setter
public class UserM {
    public UserM(Long balance, @Nullable List<Arme> inventory, Integer userID) {
        this.balance = balance;
        this.inventory = inventory;
        this.userID = userID;
    }

    @Id
    private String id;
    private Long balance;
    @Nullable
    private List<Arme> inventory;
    private Integer userID;
}
