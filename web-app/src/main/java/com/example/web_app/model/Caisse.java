package com.example.web_app.model;

import com.example.web_app.model.dto.SkinDTO;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caisse {
    private Integer id;
    private String name;
    private Long price;
    private List<SkinDTO> skins;
//    private List<String> bestLastDrop;
}
