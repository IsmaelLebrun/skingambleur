package com.example.web_app.service;

import com.example.web_app.feign.SkinFeignClient;
import com.example.web_app.model.SkinportSkin;
import com.example.web_app.model.dto.SkinDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SkinService {


    private final SkinFeignClient skinFeignClient;
    private final SkinCacheManager skinCacheManager;

    public SkinService(SkinFeignClient skinFeignClient, SkinCacheManager skinCacheManager) {
        this.skinFeignClient = skinFeignClient;
        this.skinCacheManager = skinCacheManager;
    }

    public List<SkinDTO> skinSearch(String query, Double price, Double multiplier) {
        List<SkinportSkin> skinportSkins = skinCacheManager.getCacheSkinsByPrices(price, multiplier);
        //List<SkinDTO> skinDTOs = query.isEmpty() ? skinFeignClient.getHundredFirst() : skinFeignClient.findByNameContainingIgnoreCase(query);
        List<SkinDTO> skinDTOs = skinFeignClient.findByNameContainingIgnoreCase(query);
        List<SkinDTO> returnSkinDTOs = new ArrayList<>();

        // Helper function: Extract state from market_hash_name
        Function<String, String> getSkinState = name -> {
            Pattern pattern = Pattern.compile("\\((.*?)\\)$");
            Matcher matcher = pattern.matcher(name);
            return matcher.find() ? matcher.group(0) : "";
        };

        // Helper function: Format SkinDTO names
        Function<SkinDTO, String> formatSkinDTOName = skinDTO -> {
            String name = skinDTO.getName().replace("?", "★").trim(); // Replace '?' with '★'

            // Add "StatTrak™" prefix if necessary
            if (!name.contains("StatTrak™") && skinDTO.getName().toLowerCase().contains("stattrak")) {
                name = "StatTrak™ " + name;
            }
            return name;
        };

        // Normalize and compare
        skinDTOs.forEach(skinDTO -> {
            String formattedName = formatSkinDTOName.apply(skinDTO);
            skinDTO.setName(formattedName); // Apply formatting

            for (SkinportSkin skinportSkin : skinportSkins) {
                String marketName = skinportSkin.getMarket_hash_name();
                String marketState = getSkinState.apply(marketName); // Extract state

                // Add state to DTO name for exact comparison
                String formattedDTOName = formattedName + " " + marketState;

                // If names match, set suggested price and add to results
                if (marketName.equals(formattedDTOName)) {
                    skinDTO.setName(formattedDTOName); // Finalize formatting
                    skinDTO.setSuggested_price(skinportSkin.getSuggested_price());
                    returnSkinDTOs.add(skinDTO);
                    break; // Avoid duplicate matches
                }
            }
        });

        return returnSkinDTOs;
    }

    public List<SkinDTO> getSkinsByIds(List<String> ids) {
        return skinFeignClient.getSkinsByIds(ids);
    }
}
