package com.application.masl7tak.dto;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Transactional
public class RegionDTO {

    public Long id;
    public String name_ar;
    public String name_en;

    private List<CityDTO> city;



    public RegionDTO(Long id, String name_ar, String name_en,List<CityDTO> city) {

        this.id = id;
        this.name_ar = name_ar;
        this.name_en = name_en;
        this.city = city;

    }

    public RegionDTO() {
    }

    public RegionDTO(Long id, String name_ar, String name_en) {
        this.id = id;
        this.name_ar = name_ar;
        this.name_en = name_en;

    }
}
