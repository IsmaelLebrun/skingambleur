package com.example.skins.service;

import com.example.skins.model.Skin;
import com.example.skins.model.dto.CaisseSkinDTO;
import com.example.skins.model.dto.SkinDTO;
import com.example.skins.repository.SkinRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinService {

    private SkinRepository skinRepository;

    public SkinService(SkinRepository skinRepository) {
        this.skinRepository = skinRepository;
    }

    public Skin getSkinById(String skinId) {
        return skinRepository.getReferenceById(skinId);
    }

    public List<Skin> getAllSkins() {
        return skinRepository.findAll();
    }

    public List<Skin> findByNameContainingIgnoreCaseLimitOne(String query) {
        Pageable pageable = PageRequest.of(0, 100); // Page 0, limite 100
        return skinRepository.findByNameContainingIgnoreCase(query, pageable);
    }

    public List<Skin> getHundredSkin() {
        Pageable pageable = PageRequest.of(0, 100); // Page 0, limite 100
        return skinRepository.getHundredFirst(pageable);
    }

    public CaisseSkinDTO getSKinByIdForCaisse(Skin skin) {
        return new CaisseSkinDTO(skin.getId(), skin.getName(), skin.getImage());
    }

    public List<Skin> getSkinsByIds(List<String> skinIds) {
        return skinRepository.findAllById(skinIds);
    }
}
