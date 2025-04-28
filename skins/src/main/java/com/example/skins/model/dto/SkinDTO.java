package com.example.skins.model.dto;

import com.example.skins.model.specification.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
public class SkinDTO {
    private String id;
    private String name;
    private String description;
}
