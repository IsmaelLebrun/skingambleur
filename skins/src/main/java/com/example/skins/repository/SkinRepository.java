package com.example.skins.repository;

import com.example.skins.model.Skin;
import com.example.skins.model.dto.SkinDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, String> {

    List<Skin> findByNameContainingIgnoreCase(String query, Pageable pageable);

    @Query("SELECT s FROM Skin s")
    List<Skin> getHundredFirst(Pageable pageable);
}
