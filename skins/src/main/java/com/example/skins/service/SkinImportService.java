package com.example.skins.service;

import com.example.skins.model.Skin;
import com.example.skins.repository.SkinRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class SkinImportService {

    @Autowired
    private SkinRepository skinRepository;

    private static final String API_URL = "https://bymykel.github.io/CSGO-API/api/en/skins.json";

    public void importSkins() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Lire le JSON directement en InputStream pour économiser de la mémoire
        try (InputStream responseStream = new URL(API_URL).openStream()) {
            // Convertir directement en liste d'objets Java
            Skin[] skinArray = objectMapper.readValue(responseStream, Skin[].class);
            skinRepository.saveAll(Arrays.asList(skinArray));
        }
    }
}
