package com.application.masl7tak.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {
    private Long id;
    private Long products_id;
    private String name;
    private String description;
    private double discountValue;
    private String images;
    private String creationDate;
    private String validUntil;
    private String is_available;
    private String vista_service_url_ios;
    private int quantity;
    private Long carBrand;
    private Long carModel;
    private int max_usage;
    private float rate;

    public Business business;
    public Long businessId;
    public Long eventId;
    public Long categoryId;
    public int schedule_mode;
    private Category category;
    private String vista_service_url;
    private String vista_service_code;

    private List<CarBrandEntity> carBrandEntities;
    public List<Long> getAllBrandIds() {
        if (carBrandEntities == null) {
            return Collections.emptyList();
        }

        return carBrandEntities.stream()
                .map(CarBrandEntity::aLongcarBrandId)
                .collect(Collectors.toList());
    }


    private List<CarModelEntity> carModelEntities;
    public List<Long> getAllModelIds() {
        if (carModelEntities == null) {
            return Collections.emptyList();
        }

        return carModelEntities.stream()
                .map(CarModelEntity::aLongcarModelId)
                .collect(Collectors.toList());
    }

}
