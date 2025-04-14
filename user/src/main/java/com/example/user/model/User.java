package com.example.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

}
