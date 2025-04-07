package com.example.skins.service;

import com.example.skins.model.Skin;
import com.example.skins.repository.SkinRepository;
import org.springframework.stereotype.Service;

@Service
public class SkinService {

    private SkinRepository skinRepository;

    public SkinService(SkinRepository skinRepository) {
        this.skinRepository = skinRepository;
    }

    public Skin getSkinById(String skinId) {
        return skinRepository.getReferenceById(skinId);
    }
}
