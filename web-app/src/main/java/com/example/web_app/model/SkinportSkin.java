package com.example.web_app.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkinportSkin {
    private String market_hash_name;
    private String currency;
    private Double suggested_price;
    private String item_page;
    private String market_page;
    private Double min_price;
    private Double max_price;
    private Double mean_price;
    private Double median_price;
    private Integer quantity;
    private Long created_at;
    private Long updated_at;
}
