package com.application.masl7tak.dto;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class CategoryDTO {

    public Long id;

    public   String name;

    public  String image;

    public CategoryDTO(Long id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
