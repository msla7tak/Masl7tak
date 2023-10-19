package com.application.masl7tak.dto;

import com.application.masl7tak.model.CarBrand;
import com.application.masl7tak.model.CarModel;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Transactional
public class CarBrandDTO {

    public Long id;

    public String name;

    private List<CarModelDTO> carModels;

    public CarBrandDTO(Long id, String name, List<CarModelDTO> carModels) {
        this.id = id;
        this.name = name;
        this.carModels = carModels;
    }
    public CarBrandDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }






    public CarBrandDTO() {

    }
}
