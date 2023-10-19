package com.application.masl7tak.dto;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class CarModelDTO {

    public Long id;

    public String name;
    public Long brand_id;
    public String brand_name;

    public CarModelDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarModelDTO(Long id, String name, Long brand_id, String brand_name) {
        this.id = id;
        this.name = name;
        this.brand_name = brand_name;
        this.brand_id = brand_id;
    }
    public CarModelDTO() {

    }
}
