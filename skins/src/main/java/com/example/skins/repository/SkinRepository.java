package com.example.skins.repository;

import com.example.skins.model.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkinRepository extends JpaRepository<Skin, String> {
}
