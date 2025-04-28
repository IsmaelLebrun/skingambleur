package com.example.web_app.service;

import com.example.web_app.model.SkinportSkin;
import com.example.web_app.model.CacheData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.brotli.dec.BrotliInputStream;
import org.springframework.http.*;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SkinCacheManager {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${cache.file.path}")
    private String cacheFilePath;

    public List<SkinportSkin> getCacheSkinsByPrices(Double price, Double multiplier) {
        // Charger les données du cache
        CacheData cache = readCacheFromFile();
        List<SkinportSkin> skins = new ArrayList<>();
        if(cache != null) {
                cache.skins.forEach(skin -> {
                    if(skin.getSuggested_price() != null &&
                            skin.getSuggested_price() >= price * multiplier * 0.9 &&
                            skin.getSuggested_price() <= price * multiplier * 1.1
                    ) {
                        skins.add(skin);
                    }
                });
                return skins;
        }else{
            System.out.println("La liste de skin dans le cache est vide.");
            return new CacheData().skins;
        }
    }

    private CacheData readCacheFromFile() {
        try {
            File cacheFile = new File(cacheFilePath);
            if (!cacheFile.exists()) {
                System.out.println("Cache file not found: " + cacheFile.getAbsolutePath());
                return null;
            }
            CacheData cache = objectMapper.readValue(cacheFile, CacheData.class);
            LocalDateTime lastRefresh = LocalDateTime.parse(cache.getDate(), FORMATTER);
            if(cache.date == null || lastRefresh.plusMinutes(5).isBefore(LocalDateTime.now())){
                saveCacheToFile(refreshCache());
            }
            return objectMapper.readValue(cacheFile, CacheData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveCacheToFile(List<SkinportSkin> skins) {
        if(skins == null || skins.isEmpty()){
            System.out.println("skin list is empty");
        }
        try {
            CacheData cacheData = new CacheData();
            cacheData.date = LocalDateTime.now().format(FORMATTER);
            cacheData.skins = skins;

            File cacheFile = new File(cacheFilePath);
            if (!cacheFile.getParentFile().exists()) {
                cacheFile.getParentFile().mkdirs(); // Create the directory if it doesn't exist
            }

            objectMapper.writeValue(cacheFile, cacheData);
            System.out.println("Cache saved to " + cacheFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<SkinportSkin> refreshCache() {
        Dotenv dotenv = Dotenv.load();
        String clientId = dotenv.get("SKINPORT_API_KEY");
        String clientSecret = dotenv.get("SKINPORT_API_SECRET");


        String url = "https://api.skinport.com/v1/items?app_id=730&currency=EUR";

        // Configurer les headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Encoding", "br");
        headers.set("Authorization", "Basic " + "votre_base64_encoded_cred");
        String combined = clientId + ":" + clientSecret;
        String encodedData = Base64.getEncoder().encodeToString(combined.getBytes());
        headers.set("Authorization", "Basic " + encodedData);
        headers.set("Accept-Encoding", "br");

        // Créer une requête avec les headers
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // Effectuer la requête
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
            // Décompresser les données Brotli

            byte[] compressedData = response.getBody();
            assert compressedData != null;
            BrotliInputStream brotliInputStream = new BrotliInputStream(new ByteArrayInputStream(compressedData));
            String decompressedJson = new String(brotliInputStream.readAllBytes(), StandardCharsets.UTF_8);

            // Convertir le JSON en objets Java
            ObjectMapper objectMapper = new ObjectMapper();
            SkinportSkin[] skins = objectMapper.readValue(decompressedJson, SkinportSkin[].class);

            // List<SkinportSkin> skins = List.of(response);
            List<SkinportSkin> returnSkins = List.of(skins);
            saveCacheToFile(returnSkins);
            return returnSkins;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
