package com.application.masl7tak.dto;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class CityDTO {

    public Long id;

    public String name_ar;
    public String name_en;



    public CityDTO(Long id, String name_ar, String name_en) {
        this.id = id;
        this.name_ar = name_ar;
        this.name_en = name_en;
    }

    public CityDTO() {

    }
}
