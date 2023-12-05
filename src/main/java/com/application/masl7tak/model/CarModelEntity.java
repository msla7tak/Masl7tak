package com.application.masl7tak.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_model_ids")
public class CarModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "services_id", referencedColumnName = "id")
    private Services services;

    private Long modelId;
    public CarModelEntity(String modelId) {
        this.modelId = Long.parseLong(modelId);
    }
    public Long aLongcarModelId(){
        return this.modelId;
    }
}


