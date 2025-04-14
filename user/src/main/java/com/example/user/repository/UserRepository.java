package com.example.user.repository;

import com.example.user.model.User;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(@NotEmpty(message = "L'email est obligatoire") String email);
    User findByPseudo(@NotEmpty(message = "Le pseudo est obligatoire") String pseudo);
}
