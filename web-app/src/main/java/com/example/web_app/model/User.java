package com.example.web_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String password;
    private String pseudo;
    private String avatar;
    private String tradeUrl;
    private String balanceId;
    private String inventoryId;
    private Date createdAt;

    public User(String email, String password, String pseudo) {
        this.email = email;
        this.password = password;
        this.pseudo = pseudo;
        this.createdAt = new Date();
    }
    public User() {

    }
}
