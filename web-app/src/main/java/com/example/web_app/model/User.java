package com.example.web_app.model;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "L'email est obligatoire")
    private String email;
    @NotEmpty(message = "Le mot de passe est obligatoire")
    private String password;
    @NotEmpty(message = "Le pseudo est obligatoire")
    private String pseudo;
    private String avatar;
    private String tradeUrl;
    private String userMID;
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
