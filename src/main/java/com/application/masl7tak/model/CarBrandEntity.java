package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_brand_ids")
public class CarBrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "services_id", referencedColumnName = "id")
    private Services services;

    private Long brandId;

    public CarBrandEntity(String brandId) {
        this.brandId =Long.parseLong( brandId);
    }

    public Long aLongcarBrandId(){
        return this.brandId;
    }
}


