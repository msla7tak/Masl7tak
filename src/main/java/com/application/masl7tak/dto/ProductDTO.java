package com.application.masl7tak.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class ProductDTO {


    private Long id;

    private String name;

    private String description;



    private String image;

    public ProductDTO(Long id, String name, String description,String image, Long business) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;

    }

    public ProductDTO(Long id, String name, String description,  String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
